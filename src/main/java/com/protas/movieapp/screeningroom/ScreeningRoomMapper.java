package com.protas.movieapp.screeningroom;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScreeningRoomMapper {
    ScreeningRoomDTO fromEntity(ScreeningRoom screeningRoom);
    ScreeningRoom fromDTO(ScreeningRoomDTO screeningRoomDTO);
}
