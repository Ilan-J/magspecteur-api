package com.magspecteur.api.domain;

import java.util.List;

public record PublisherDTO(String name, String address, List<Theme> themes) {
}
