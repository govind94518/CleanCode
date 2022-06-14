package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

import java.util.List;

public class UserReportBuilder {

    private UserDao userDao;

    public Double getUserTotalOrderAmount(String userId) {
        checkNoUserDaoObject();
        User user = findUserById(userId);
        List<Order> orders = getOrderForUser(user);
        return calculateTotalOrdersSum(orders);
    }

    public List<Order> getOrderForUser(User user) {
        List<Order> orders = user.getAllOrders();
        validateEmptyOrder(orders);
        return orders;
    }

    private User findUserById(String userId) {
        User user = userDao.getUser(userId);
        validateUserOnNull(user);
        return user;
    }

    private Double calculateTotalOrdersSum(List<Order> orders) {
        Double sum = 0.0;
        for (Order order : orders) {
            sum += getOrderTotal(order);
        }
        return sum;
    }

    private Double getOrderTotal(Order order) {
        Double total = 0.0;
        if (order.isSubmitted()) {
            validateInvalidOrderTotal(order);
            total = order.total();
        }
        return total;
    }

    private void validateInvalidOrderTotal(Order order) {
        if (order.total() < 0)
            throw new WrongOrderAmountException();
    }

    private void validateEmptyOrder(List<Order> orders) {
        if (orders.isEmpty())
            throw new NoOrderExistException();
    }

    private void validateUserOnNull(User user) {
        if (user == null)
            throw new UserNotExistException();
    }

    public void checkNoUserDaoObject() {
        if (userDao == null)
            throw new TechnicalErrorException();

    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
