@Component
public class TransactionKafkaConsumer {

    @Autowired
    private KafkaProperties kafkaProperties;

    public List<Transaction> consumeTransactions() {
        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TransactionDeserializer.class);

        try (KafkaConsumer<String, Transaction> consumer = new KafkaConsumer<>(consumerProps)) {
            consumer.subscribe(Collections.singletonList(kafkaProperties.getConsumer().getTopic()));

            List<Transaction> transactions = new ArrayList<>();

            while (true) {
                ConsumerRecords<String, Transaction> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, Transaction> record : records) {
                    Transaction transaction = record.value();
                    transactions.add(transaction);
                }

                consumer.commitSync(); // Optional: commit offsets manually to manage offsets
            }

            return transactions;
        } catch (Exception e) {
            // Handle any exceptions that occur during consumption
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
