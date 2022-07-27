package com.example.springexam.aop.executor;//package ai.ecma.appspringsecurity.aop.executor;
//
//import ai.ecma.sales.aop.annotation.CheckAuth;
//import ai.ecma.sales.component.MessageService;
//import ai.ecma.sales.enums.PermissionEnum;
//import ai.ecma.sales.exception.RestException;
//import ai.ecma.sales.payload.UserDTO;
//import ai.ecma.sales.service.CacheService;
//import ai.ecma.sales.utils.AppConstant;
//import ai.ecma.sales.utils.CommonUtils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.Objects;
//
//import static ai.ecma.sales.utils.CommonUtils.currentRequest;
//
//
///**
// * @author Muhammad Mo'minov
// * 06.11.2021
// */
//
//@Order(value = 1)
//@Aspect
//@Component
//@RequiredArgsConstructor
//public class CheckAuthAspect {
//
//    @Before(value = "@annotation(checkAuth)")
//    public void checkAuthExecutor(CheckAuth checkAuth) {
//        check(checkAuth);
//    }
//
//    private String getTokenFromRequest(HttpServletRequest httpServletRequest) {
//        try {
//            return httpServletRequest.getHeader(AppConstant.AUTHENTICATION_HEADER);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
//        }
//    }
//
//    public void check(CheckAuth checkAuth) {
//
//        HttpServletRequest httpServletRequest = currentRequest();
//
//        boolean requestIsNotNull = Objects.nonNull(httpServletRequest);
//
//        String token = requestIsNotNull ? getTokenFromRequest(httpServletRequest) : CommonUtils.getTokenFromCustomThread();
//
//        if (token == null)
//            throw RestException.restThrow(MessageService.getMessage("FORBIDDEN"), HttpStatus.FORBIDDEN);
//
//        String userIdFromRequest;
//        try {
//            userIdFromRequest = requestIsNotNull ? CommonUtils.getUserIdFromRequest(httpServletRequest) : CommonUtils.getUserIdFromCustomThread();
//        } catch (Exception e) {
//            throw RestException.restThrow(MessageService.getMessage("FORBIDDEN"), HttpStatus.FORBIDDEN);
//        }
//
//        if (userIdFromRequest == null || userIdFromRequest.isEmpty()) {
//            UserDTO userDTO = cacheService.getUserDTOFromAuthByToken(token);
//            PermissionEnum[] permission = checkAuth.permission();
//            if (permission.length > 0 && notPermission(userDTO.getPermissions(), permission))
//                throw RestException.restThrow(MessageService.getMessage("FORBIDDEN"), HttpStatus.FORBIDDEN);
//
//            setUserAndIdAndPermissionsToRequestAttribute(httpServletRequest, userDTO);
//        } else {
//            if (checkAuth.permission().length > 0 && notPermission(requestIsNotNull ? CommonUtils.getUserPermissionsFromRequest(httpServletRequest) : CommonUtils.getUserPermissionsFromCustomThread(), checkAuth.permission()))
//                throw RestException.restThrow(MessageService.getMessage("FORBIDDEN"), HttpStatus.FORBIDDEN);
//
//            setUserIdAndPermissionFromRequest(httpServletRequest);
//        }
//    }
//
//    private void setUserAndIdAndPermissionsToRequestAttribute(HttpServletRequest httpServletRequest, UserDTO userDTO) {
//        httpServletRequest.setAttribute(AppConstant.REQUEST_ATTRIBUTE_CURRENT_USER, userDTO);
//        httpServletRequest.setAttribute(AppConstant.REQUEST_ATTRIBUTE_CURRENT_USER_ID, userDTO.getId());
//        httpServletRequest.setAttribute(AppConstant.REQUEST_ATTRIBUTE_CURRENT_USER_PERMISSIONS, userDTO.getPermissions());
//    }
//
//    public void check(String token, PermissionEnum[] permissions) {
//
//        if (token == null) {
//            throw RestException.restThrow(MessageService.getMessage("FORBIDDEN"), HttpStatus.FORBIDDEN);
//        }
//
//        UserDTO userDTO = cacheService.getUserDTOFromAuthByToken(token);
//
//        if (permissions.length > 0 && notPermission(userDTO.getPermissions(), permissions))
//            throw RestException.forbidden();
//    }
//
//    private boolean notPermission(List<String> hasPermission, PermissionEnum[] mustPermission) {
//        if (hasPermission == null)
//            return true;
//        for (PermissionEnum permissionEnum : mustPermission) {
//            if (hasPermission.contains(permissionEnum.name()))
//                return false;
//        }
//        return true;
//    }
//
//    private boolean notPermission(String permission, PermissionEnum[] mustPermission) {
//        if (permission == null || permission.isEmpty())
//            return true;
//        for (PermissionEnum permissionEnum : mustPermission) {
//            if (permission.contains(permissionEnum.name()))
//                return false;
//        }
//        return true;
//    }
//
//    private void setUserIdAndPermissionFromRequest(HttpServletRequest httpServletRequest) {
//        if (Objects.isNull(httpServletRequest)) return;
//
//        String userId = CommonUtils.getUserIdFromRequest(httpServletRequest);
//        String permissions = CommonUtils.getUserPermissionsFromRequest(httpServletRequest);
//
//        httpServletRequest.setAttribute(AppConstant.REQUEST_ATTRIBUTE_CURRENT_USER_ID, userId);
//        httpServletRequest.setAttribute(AppConstant.REQUEST_ATTRIBUTE_CURRENT_USER_PERMISSIONS, permissions);
//    }
//}
