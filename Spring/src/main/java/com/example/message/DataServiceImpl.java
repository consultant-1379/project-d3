//package com.example.message;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class DataServiceImpl implements DataService {
//
//    private TableRepository repo;
//
//    @Autowired
//    public DataServiceImpl(TableRepository repo) {
//        this.repo = repo;
//    }
//
//    @Override
//    public List<DemoData> getAllData() {
//        return repo.findAll();
//    }
//
//    @Override
//    public DemoData getById(int id) {
//        Optional<DemoData> optional = repo.findById(id);
//        DemoData data = null;
//        if (optional.isPresent())
//            data = optional.get();
//        else
//            throw new RuntimeException(
//                    "Data not found for id : " + id);
//        return data;
//    }
//}
