package com.task.atipera.controller;

import com.task.atipera.client.GitHubRepositoryClient;
import com.task.atipera.model.GitHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class GitHubRepositoryController {
    @Autowired
    private GitHubRepositoryClient gitHubRepositoryClient;

    @GetMapping("/{username}")
    public List<GitHubRepository> getReposForUser(@PathVariable final String username) throws URISyntaxException, IOException, InterruptedException {
        return gitHubRepositoryClient.getReposFromUser(username);
    }
}
