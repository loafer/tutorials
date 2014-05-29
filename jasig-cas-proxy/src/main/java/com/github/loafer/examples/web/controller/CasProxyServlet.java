package com.github.loafer.examples.web.controller;

import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;

/**
 * Created by zhaojh on 2014/5/29.
 */
public class CasProxyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetUrl = "http://localhost:8082/cas-client/user";
        Assertion assertion = (Assertion)request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        String pgt = assertion.getPrincipal().getProxyTicketFor(targetUrl);

        System.out.println("PGT: " + pgt);

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8082/cas-client").path("user").queryParam("ticket", pgt);
        String result = target.request().get(String.class);
        System.out.println(request);

        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
