package org.softuni.nuggets.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MapServiceImpl implements MapService {

    @Override
    public String getMap() {
        StringBuilder result = new StringBuilder();

        result
                .append("{")
                .append("   \"type\": \"FeatureCollection\",")
                .append("   \"features\": [");


        result
                .append("{")
                .append("\"type\": \"Feature\",")
                .append("\"properties\": {")
                .append("\"mag\": " + 5 + ",")
                .append("\"color\": \"#F00\"")
                .append("},")
                .append("\"geometry\": {")
                .append("\"type\": \"Point\",")
                .append("\"coordinates\": [")
                .append(42.7171265)
                .append(",")
                .append(23.2598598)
                .append("]")
                .append("}")
                .append("},");
        result.replace(result.length() - 1, result.length(), "");

        result
                .append("]")
                .append("}");

        return result.toString();
    }
}
