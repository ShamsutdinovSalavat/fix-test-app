package ru.fix.service.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import ru.fix.service.services.HorseMoveService;
import ru.fix.service.transfer.ParametersDto;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/horse/servlet/count")
public class HorseMoveServlet extends HttpServlet {

    @Autowired
    private HorseMoveService horseMoveService;

    private List<String> requiredParameters;

    @Override
    public void init() {
        requiredParameters = new ArrayList<>();
        requiredParameters.add("width");
        requiredParameters.add("height");
        requiredParameters.add("start");
        requiredParameters.add("end");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ParametersDto parametersDto = new ParametersDto();

        try {
            if (hasRequiredParameters(request)) {
                Map<String, String> parametersMap = requiredParameters
                        .stream()
                        .collect(Collectors.toMap(par -> par, request::getParameter));

                parametersDto.setWidth(Integer.valueOf(parametersMap.get("width")));
                parametersDto.setHeigth(Integer.valueOf(parametersMap.get("height")));
                parametersDto.setStart(parametersMap.get("start"));
                parametersDto.setEnd(parametersMap.get("end"));


                Integer value = horseMoveService.getValue(parametersDto);
                sendValue(response, value);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (IllegalArgumentException exc) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private boolean hasRequiredParameters(ServletRequest request) {
        return requiredParameters
                .stream()
                .allMatch(str -> Collections.list(request.getParameterNames()).contains(str));
    }

    private void sendValue(HttpServletResponse response, Integer value) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        out.print(value);
        out.flush();
    }
}
