package com.task.atipera.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.task.atipera.exception.UserNotFoundException;
import com.task.atipera.model.Branch;
import com.task.atipera.model.GitHubRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubRepositoryClient {
    public List<GitHubRepository> getReposFromUser(final String username) throws URISyntaxException, IOException, InterruptedException {
        JsonObject[] repositoryObjects = getObjectsFromUri(STR."https://api.github.com/users/\{username}/repos");
        List<GitHubRepository> repositories = new ArrayList<>();

        for(JsonObject repositoryObject : repositoryObjects) {
            if(repositoryObject.get("fork").getAsBoolean() || repositoryObject.get("private").getAsBoolean()) {
                continue;
            }

            JsonObject[] branchObjects = getObjectsFromUri(
                    STR."https://api.github.com/repos/\{username}/\{repositoryObject.get("name").getAsString()}/branches");
            List<Branch> branches = new ArrayList<>();

            for(JsonObject branchObject : branchObjects) {
                branches.add(new Branch(branchObject.get("name").getAsString(),
                        branchObject.get("commit").getAsJsonObject().get("sha").getAsString()));
            }

            repositories.add(new GitHubRepository(repositoryObject.get("name").getAsString(),
                    repositoryObject.get("owner").getAsJsonObject().get("login").getAsString(),
                    branches));
        }
        return repositories;
    }

    private JsonObject[] getObjectsFromUri(final String uri) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest repositoriesRequest = HttpRequest.newBuilder(new URI(uri)).build();
        HttpResponse<String> repositoriesResponse = HttpClient.newHttpClient()
                .send(repositoriesRequest, HttpResponse.BodyHandlers.ofString());
        if(repositoriesResponse.statusCode() == 404) {
            throw new UserNotFoundException("The requested GitHub user does not exist");
        }

        Gson gson = new GsonBuilder().create();
        return gson.fromJson(repositoriesResponse.body(), JsonObject[].class);
    }
}
