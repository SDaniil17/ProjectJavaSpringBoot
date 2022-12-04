package com.sdv.FoodService.Controllers.RestContollers;

import com.sdv.FoodService.Models.Food;
import com.sdv.FoodService.Services.IFoodService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/foods")
public class FoodRestController {

    @Autowired
    private IFoodService foodService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Food>GetFoodsByTitle(@RequestParam String title) {

        Food food = foodService.FindByName(title);
        if(food == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(food,HttpStatus.OK);
    }

    @Operation(summary = "Fetch all food in the page")
    @RequestMapping(value = "{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Food>> GetFoodsPages(@PathVariable(value = "page") Integer page) {

        List<Food> foods = foodService.getPage(PageRequest.of(page,8)).toList();

        if(foods.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(foods,HttpStatus.OK);
    }

    @Operation(summary = "Get count of foods pages")
    @RequestMapping(value = "/pages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetPagesCount(){

        int pagesCount = foodService.getPage(PageRequest.of(1,8)).getTotalPages();
        Map<Object, Object> response = new HashMap<>();
        response.put("count", pagesCount);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
