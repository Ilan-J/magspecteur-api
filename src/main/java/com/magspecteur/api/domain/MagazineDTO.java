package com.magspecteur.api.domain;

import java.util.Date;

public record MagazineDTO(String name, Integer number, Date releaseDate, Integer publisherId) {
}
