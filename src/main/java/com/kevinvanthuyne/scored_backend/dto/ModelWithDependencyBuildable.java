package com.kevinvanthuyne.scored_backend.dto;

public interface ModelWithDependencyBuildable<T, D> {
    T buildModel(D dependency);
}
