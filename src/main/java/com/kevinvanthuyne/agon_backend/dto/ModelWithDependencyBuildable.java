package com.kevinvanthuyne.agon_backend.dto;

public interface ModelWithDependencyBuildable<T, D> {
    T buildModel(D dependency);
}
