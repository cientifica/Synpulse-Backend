@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<TransactionListDto> getTransactions(
            @RequestParam("customer_id") String customerId,
            @RequestParam("month") String month,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "20") int pageSize) {

        try {
            // Retrieve transactions for the given customer and month
            TransactionListDto transactions = transactionService.getTransactions(customerId, month, page, pageSize);

            // Return the transactions in the response body
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            // Handle any exceptions and return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
