package com.magspecteur.api.domain;

import java.util.Collection;

public record PublisherDTO(String name, String address, Collection<Theme> themes) {
}
