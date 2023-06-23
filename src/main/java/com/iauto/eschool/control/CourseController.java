package com.iauto.eschool.control;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iauto.eschool.dto.CourseDto;
import com.iauto.eschool.dto.PageDTO;
import com.iauto.eschool.entity.Category;
import com.iauto.eschool.entity.Course;
import com.iauto.eschool.entity.User;
import com.iauto.eschool.mapper.CourseMapper;
import com.iauto.eschool.service.CourseService;
import com.iauto.eschool.service.UserService;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@RestController
@RequestMapping("courses")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private UserService userService;

	@PreAuthorize("hasAuthority('course:write')")
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody CourseDto courseDto) {
//		Category category = new Category();
//		category.setId(1l);
//		course.setCategory(category);
 		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		System.out.println("authentication.getName:"+username);
		User user = userService.getByUsername(username);
		System.out.println("user.username:"+user.getUsername());
		courseDto.setUserId(user.getId());
		
		
		Course course = courseMapper.toCourse(courseDto);
		//Course course = CourseMapper.INSTANCE.toCourse(courseDto);
		
		CourseDto courseDtoResp = courseMapper.toCourseDto( courseService.creat(course) );
		//CourseDto courseDtoResp = CourseMapper.INSTANCE.toCourseDto( courseService.creat(course) );
		
		return ResponseEntity.ok( courseDtoResp );
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		Course course = courseService.getById(id);
		CourseDto courseDto = CourseMapper.INSTANCE.toCourseDto(course);
		
		return ResponseEntity.ok(courseDto);
	}
	 
	@PreAuthorize("hasAuthority('course:update')")
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CourseDto courseDto){
		
		
		Course course = courseMapper.toCourse(courseDto);
		course.setId(id);
		CourseDto courseDtoResp = courseMapper.toCourseDto( courseService.update(id, course) );
		return ResponseEntity.ok(courseDtoResp);
	}
	
	@PreAuthorize("hasAuthority('course:delete')")
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		courseService.delete(id);
		return ResponseEntity.ok(null);
	}
	
	
	@GetMapping
	public ResponseEntity<?> getCourse(@RequestParam Map<String, String> params){
		
//		Page<Course> coursesPage = courseService.getCourses(params);
//		PageDTO pageDTO = new PageDTO(coursesPage);
//		return ResponseEntity.ok(pageDTO);
		
		Page<CourseDto> coursesPageDto = courseService.getCoursesDto(params);
		PageDTO pageDTO = new PageDTO(coursesPageDto);
		return ResponseEntity.ok(pageDTO);
	}
}
