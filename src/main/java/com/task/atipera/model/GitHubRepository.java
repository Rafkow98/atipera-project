package com.task.atipera.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GitHubRepository {
    private String name;

    private String owner;

    private List<Branch> branches;
}
