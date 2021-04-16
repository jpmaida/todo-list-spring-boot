package com.laboratory.resources;

import com.laboratory.domain.todolist.ToDo;
import com.laboratory.domain.todolist.ToDoList;
import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Java6Assertions.*;

@WebMvcTest
public class ToDoListApiTest {

	@MockBean
	private ToDoList toDoList;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getToDoList() throws Exception {
		ToDo toDo1 = new ToDo("REST Assured With Spring Boot");
		ToDo toDo2 = new ToDo("X");
		ToDo toDo3 = new ToDo("Y");
		toDo3.setIsDone(true);
		ToDo toDo4 = new ToDo("Z");
		ToDo toDo5 = new ToDo("W");
		Mockito.when(toDoList.getList()).thenReturn(
				List.of(toDo1,toDo2,toDo3,toDo4,toDo5));

		this.mockMvc
			.perform(get("/api/todolist"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value(hasSize(5)))
			.andExpect(jsonPath("$[0].description").value("REST Assured With Spring Boot"))
			.andExpect(jsonPath("$[2].done").value(true))
			.andExpect(jsonPath("$[3].description").value("Z"));
	}

	@Test
	void getSingleToDo() throws Exception{
		ToDo toDo = new ToDo("Some crazy description");
		long id = toDo.getId();
		String description = toDo.getDescription();
		boolean isDone = toDo.isDone();

		Mockito.when(toDoList.getToDo(id)).thenReturn(toDo);

		this.mockMvc
				.perform(get("/api/todolist/"+id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(3)))
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.description").value(description))
				.andExpect(jsonPath("$.done").value(isDone));
	}

	/*
	@Test
	void addToDo() throws Exception{
		String jsonPayload = "{\"description\" : \"Some cool description\", \"isDone\" : \"true\"}";
		ToDo toDo = new ToDo("Some cool description");
		toDo.setIsDone(true);
		Mockito.when(this.toDoList.add(toDo)).thenReturn(toDo);
		this.mockMvc
				.perform(post("/api/todolist")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonPayload))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isCreated());
	}
	 */
}
