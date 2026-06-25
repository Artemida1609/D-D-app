package mate.academy.jvteamproject.mapper;

public interface GenericMapper<D, E> {
    E toEntity(D dto);
}
