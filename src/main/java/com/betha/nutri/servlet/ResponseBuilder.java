package com.betha.nutri.servlet;

import java.util.List;

public class ResponseBuilder {

    public String buildFromList(List list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder response = new StringBuilder();
            response.append("[");

            for (Object item : list) {
                response.append(item.toString());
                response.append(",");
            };

            response.replace(response.lastIndexOf(","), response.length(), "");
            response.append("]");
            return response.toString();
        }

        return "[]";
    }

}
