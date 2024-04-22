public class Customer extends  Thread {
    private final Account account;
    private final Shop[] shops;
    private final Bank bank;

    public Customer(Account account, Shop[] shops, Bank bank) {
        this.account = account;
        this.shops = shops;
        this.bank = bank;
    }

    @Override
    public void run() {
        while (account.getBalance() >= 100.0) {
            for (Shop shop : shops) {
                if (account.getBalance() >= 100.0) {
                    bank.transfer(account, bank.findAccountByRoutingNumber(shop.getRoutingNumber()), 100.0);
                }
                if (account.getBalance() >= 200.0) {
                    bank.transfer(account, bank.findAccountByRoutingNumber(shop.getRoutingNumber()), 200.0);
                }
            }
        }
    }
}
