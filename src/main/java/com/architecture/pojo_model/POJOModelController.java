package com.architecture.pojo_model;
import static spark.Spark.*;
import static com.architecture.pojo_model.JsonUtil.*;
import java.util.*;


public class POJOModelController {

	public POJOModelController(final POJOModelService pojoService) {

		get("/all", (req, res) -> pojoService.getAllComponents(), json());

//		get("/component/:id", (req, res) -> {
//			String id = req.params(":id");
//			if (user != null) {
//				return pojoService.getComponent(id);
//			}
//			res.status(400);
//			return new ResponseError("No pojo with id '%s' found", id);
//		}, json());

		post("/create_namespaced", (req, res) -> {
			String namespace = req.queryParams("namespace");
			String path = req.queryParams("filepath");
		 	String type = req.queryParams("type");
			String[] array = {namespace, path};
			POJOModelAction action = new POJOModelAction(type);
			String result = action.create(array);
			return result;
		}, json());
		
		post("/update_namespaced", (req, res) -> {
			String name = req.queryParams("name");
			String namespace = req.queryParams("namespace");
			String path = req.queryParams("filepath");
		 	String type = req.queryParams("type");
			String[] array = {name, namespace, path};
			POJOModelAction action = new POJOModelAction(type);
			String result = action.update(array);
			return result;
		}, json());
		
		post("/delete_namespaced", (req, res) -> {
			String namespace = req.queryParams("namespace");
			String name = req.queryParams("name");
		 	String type = req.queryParams("type");
			String[] array = {name, namespace};
			POJOModelAction action = new POJOModelAction(type);
			String result = action.delete(array);
			System.out.println(result);
			return result;
		}, json());
		post("/create", (req, res) -> {
			String path = req.queryParams("filepath");
			String type = req.queryParams("type");
			String[] array = {path};
			POJOModelAction action = new POJOModelAction(type);
			String result = action.create(array);
			return result;
		}, json());
		
		post("/update", (req, res) -> {
			String name = req.queryParams("name");
			String path = req.queryParams("filepath");
			String type = req.queryParams("type");
			String[] array = {name, path};
			POJOModelAction action = new POJOModelAction(type);
			String result = action.update(array);
			return result;
		}, json());
		
		post("/delete", (req, res) -> {
			String name = req.queryParams("name");
			String type = req.queryParams("type");
			String[] array = {name};
			POJOModelAction action = new POJOModelAction(type);
			String result = action.delete(array);
			return result;
		}, json());
//
//
//
//		put("/users/:id", (req, res) -> userService.updateUser(
//				req.params(":id"),
//				req.queryParams("name"),
//				req.queryParams("email")
//		), json());

		after((req, res) -> {
			res.type("application/json");
		});

		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(400);
			res.body(toJson(new ResponseError(e)));
		});
	}
}
