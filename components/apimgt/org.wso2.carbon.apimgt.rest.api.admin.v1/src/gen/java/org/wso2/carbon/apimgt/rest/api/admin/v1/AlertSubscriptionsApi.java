package org.wso2.carbon.apimgt.rest.api.admin.v1;

import org.wso2.carbon.apimgt.rest.api.admin.v1.dto.AlertsSubscriptionDTO;
import org.wso2.carbon.apimgt.rest.api.admin.v1.dto.ErrorDTO;
import org.wso2.carbon.apimgt.rest.api.admin.v1.AlertSubscriptionsApiService;
import org.wso2.carbon.apimgt.rest.api.admin.v1.impl.AlertSubscriptionsApiServiceImpl;
import org.wso2.carbon.apimgt.api.APIManagementException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.inject.Inject;

import io.swagger.annotations.*;
import java.io.InputStream;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
@Path("/alert-subscriptions")

@Api(description = "the alert-subscriptions API")
@Consumes({ "application/json" })
@Produces({ "application/json" })


public class AlertSubscriptionsApi  {

  @Context MessageContext securityContext;

AlertSubscriptionsApiService delegate = new AlertSubscriptionsApiServiceImpl();


    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Get the list of API Admin alert types subscribed by the user. ", notes = "This operation is used to get the list of subscribed alert types by the user. ", response = AlertsSubscriptionDTO.class, authorizations = {
        @Authorization(value = "OAuth2Security", scopes = {
            @AuthorizationScope(scope = "apim:admin_alert_manage", description = "Manage admin alerts"),
            @AuthorizationScope(scope = "apim:admin", description = "Manage all admin operations")
        })
    }, tags={ "Alert Subscriptions",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK. The list of subscribed alert types are returned. ", response = AlertsSubscriptionDTO.class),
        @ApiResponse(code = 500, message = "Internal Server Error An error occurred while retrieving subscribed alert types by user. ", response = ErrorDTO.class) })
    public Response getSubscribedAlertTypes() throws APIManagementException{
        return delegate.getSubscribedAlertTypes(securityContext);
    }

    @PUT
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Subscribe to an admin alert. ", notes = "This operation is used to subscribe to admin alerts ", response = AlertsSubscriptionDTO.class, authorizations = {
        @Authorization(value = "OAuth2Security", scopes = {
            @AuthorizationScope(scope = "apim:admin_alert_manage", description = "Manage admin alerts"),
            @AuthorizationScope(scope = "apim:admin", description = "Manage all admin operations")
        })
    }, tags={ "Alert Subscriptions",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK. Successful response with the newly subscribed alerts. ", response = AlertsSubscriptionDTO.class),
        @ApiResponse(code = 400, message = "Bad Request. Invalid Request or request validation failure. ", response = Void.class),
        @ApiResponse(code = 500, message = "Internal Server Error An internal server error occurred while subscribing to alerts. ", response = ErrorDTO.class) })
    public Response subscribeToAlerts(@ApiParam(value = "The alerts list and the email list to subscribe." ,required=true) AlertsSubscriptionDTO body) throws APIManagementException{
        return delegate.subscribeToAlerts(body, securityContext);
    }

    @DELETE
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Unsubscribe user from all the admin alert types. ", notes = "This operation is used to unsubscribe the respective user from all the admin alert types. ", response = Void.class, authorizations = {
        @Authorization(value = "OAuth2Security", scopes = {
            @AuthorizationScope(scope = "apim:admin_alert_manage", description = "Manage admin alerts"),
            @AuthorizationScope(scope = "apim:admin", description = "Manage all admin operations")
        })
    }, tags={ "Alert Subscriptions" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK. The user is unsubscribed from the alerts successfully. ", response = Void.class),
        @ApiResponse(code = 500, message = "Internal Server Error ", response = ErrorDTO.class) })
    public Response unsubscribeAllAlerts() throws APIManagementException{
        return delegate.unsubscribeAllAlerts(securityContext);
    }
}
