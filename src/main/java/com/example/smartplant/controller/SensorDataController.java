package com.example.smartplant.controller;

import com.example.smartplant.model.SensorData;
import com.example.smartplant.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/sensors")
public class SensorDataController { // REST API를 통해 센서 데이터를 관리하는 컨트롤러

    @Autowired
    private SensorDataService sensorDataService;

    // 모든 센서 데이터 조회
    @GetMapping
    public CompletableFuture<List<SensorData>> getSensorData() {
        return sensorDataService.getAllSensorData();
    }

    // 센서 데이터 추가
    @PostMapping
    public CompletableFuture<Void> addSensorData(@RequestBody SensorData sensorData) {
        sensorData.setTimestamp(System.currentTimeMillis());
        return sensorDataService.saveSensorData(sensorData);
    }

    // 센서 데이터 업데이트
    @PutMapping("/{id}")
    public CompletableFuture<Void> updateSensorData(@PathVariable String id, @RequestBody SensorData sensorData) {
        sensorData.setId(id);
        sensorData.setTimestamp(System.currentTimeMillis());
        return sensorDataService.updateSensorData(sensorData);
    }

    // 센서 데이터 삭제
    @DeleteMapping("/{id}")
    public CompletableFuture<Void> deleteSensorData(@PathVariable String id) {
        return sensorDataService.deleteSensorData(id);
    }
}