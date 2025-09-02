package mapper;

public interface Mapper<F, T>{

    T mapToEntity(F object);
    F mapToDto(T object);

}
