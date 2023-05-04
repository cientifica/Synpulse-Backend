//Modify the API endpoint in the controller:
@GetMapping("/api/transactions")
public ResponseEntity<TransactionList> getTransactions(@RequestParam("customer_id") String customerId,
                                                       @RequestParam("month") String month,
                                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "page_size", defaultValue = "20") int pageSize) {

    // Call the service layer method to retrieve the paginated list of transactions
    TransactionList transactionList = transactionService.getPaginatedTransactions(customerId, month, page, pageSize);

    return ResponseEntity.ok(transactionList);
}
//Update the service layer method to handle pagination and return a paginated result:
public TransactionList getPaginatedTransactions(String customerId, String month, int page, int pageSize) {
    // Calculate the offset and limit for pagination
    int offset = (page - 1) * pageSize;
    
    // Call the method to retrieve the transactions with pagination
    List<Transaction> transactions = transactionRepository.getTransactionsByCustomerIdAndMonth(customerId, month, offset, pageSize);

    // Get the total count of transactions for the given criteria
    int totalCount = transactionRepository.getTransactionCountByCustomerIdAndMonth(customerId, month);

    // Perform any additional processing, such as calculating total credit and debit values

    // Create the TransactionList object with the paginated transactions and pagination information
    TransactionList transactionList = new TransactionList();
    transactionList.setTransactions(transactions);
    transactionList.setTotalCount(totalCount);
    transactionList.setPage(page);
    transactionList.setPageSize(pageSize);

    return transactionList;
}
/*Update the response schema in the OpenAPI specification to include pagination information. You can add the page, page_size, and total_count properties to the TransactionList schema.

The TransactionList class should include properties for the paginated transactions, total count, page number, and page size. */