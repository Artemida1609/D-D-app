package mate.academy.jvteamproject.importer;

import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.repository.GeneralRepository;

public record ImportDefinition<D, E>(
        String endpoint,
        Class<D> dtoClass,
        GenericMapper<D, E> mapper,
        GeneralRepository<E> repository
) {}
