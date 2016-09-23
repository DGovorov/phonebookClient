package com.client.service;

import com.client.entity.Record;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecordRESTCunsumer {

    final String uri = "http://localhost:8081/recordsapi";
    private RestTemplate rest = new RestTemplate();

    public Record[] findAll() {
        ResponseEntity<Record[]> response = rest.getForEntity(uri, Record[].class);
        Record[] records = response.getBody();
        return records;
    }

    public void delete(long id) {
        rest.delete(uri + "/" + id);
    }

    public Record create(Record record) {
        return rest.postForObject(uri, record, Record.class);
    }

    public void update(Record record) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(record.getId()));
        params.put("name", record.getName());
        params.put("surname", record.getSurname());
        params.put("phoneNumber", record.getPhoneNumber());
        params.put("email", record.getEmail());
        rest.put(uri + "/" + record.getId(), record, params);
    }

    public Record findOne(long id) {
        ResponseEntity<Record> response = rest.getForEntity(uri + "/" + id, Record.class);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            System.out.println("NOT FOUND");
        }
        return response.getBody();
    }
}
