<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>账户管理</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="account-container">
    <h2>账户管理</h2>

    <!-- 查询余额 -->
    <h3>查询余额</h3>
    <form action="BalanceServlet" method="POST">
        <label for="accountId">账户ID</label>
        <input type="text" id="accountId" name="accountId" required>
        <button type="submit">查询</button>
    </form>

    <!-- 存钱 -->
    <h3>存钱</h3>
    <form action="DepositServlet" method="POST">
        <label for="depositAmount">存入金额</label>
        <input type="number" id="depositAmount" name="depositAmount" required>
        <button type="submit">存款</button>
    </form>

    <!-- 取钱 -->
    <h3>取钱</h3>
    <form action="WithdrawServlet" method="POST">
        <label for="withdrawAmount">取出金额</label>
        <input type="number" id="withdrawAmount" name="withdrawAmount" required>
        <button type="submit">取款</button>
    </form>

    <!-- 转账 -->
    <h3>转账</h3>
    <form action="TransferServlet" method="POST">
        <label for="fromAccountId">转出账户ID</label>
        <input type="text" id="fromAccountId" name="fromAccountId" required>

        <label for="toAccountId">转入账户ID</label>
        <input type="text" id="toAccountId" name="toAccountId" required>

        <label for="transferAmount">转账金额</label>
        <input type="number" id="transferAmount" name="transferAmount" required>

        <button type="submit">转账</button>
    </form>

    <!-- 生成年终报告 -->
    <h3>年终报告</h3>
    <form action="ReportServlet" method="GET">
        <button type="submit">生成年终报告（PDF）</button>
    </form>

</div>
</body>
</html>
