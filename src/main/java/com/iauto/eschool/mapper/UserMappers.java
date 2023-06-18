package com.iauto.eschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.iauto.eschool.dto.UserDTO;
import com.iauto.eschool.entity.Role;
import com.iauto.eschool.entity.User;
import com.iauto.eschool.service.CourseService;
import com.iauto.eschool.service.RoleService;

@Mapper(componentModel = "spring", uses = {RoleService.class})
public interface UserMappers {
	
	UserMappers INSTANCE = Mappers.getMapper(UserMappers.class);
	
	//@Mapping(target = "roles", source = "roleId")
	User toUser(UserDTO userDTO);
	
	//@Mapping(target = "roleId", source="user.roles")
	UserDTO toUserDto(User user);
	
	
	
}
