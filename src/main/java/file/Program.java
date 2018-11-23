package file;

import data.Registry;

/**
 * Pagrindine programos logika
 * Meniu punkto pasirinkimas ir atitinkamu veiksmu vykdymas
 */
public class Program {

    /**
     * Programos pradzia
     */
    public void start() {
        Registry registry;
        Dialog dialog = new Dialog();

        registry = Storage.load();

        while (true) {
            dialog.showMenu();
            switch (dialog.inputMenuItem()) {
                case "1":
                    try {
                        registry.add(dialog.inputCredit());
                        dialog.show("Pajamos pridetos");
                    } catch (IllegalArgumentException e) {
                        dialog.show(e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        registry.add(dialog.inputDebit());
                        dialog.show("Islaidos pridetos");
                    } catch (IllegalArgumentException e) {
                        dialog.show(e.getMessage());
                    }
                    break;
                case "3":
                    dialog.showList(registry.getList());
                    break;
                case "4":
                    dialog.show("Bendras balansas: " + registry.calculateBalance());
                    break;
                case "0":
                    if (Storage.save(registry)) {
                        dialog.show("Tranzakciju registras issaugotas");
                        return;
                    } else {
                        dialog.show("Nepavyko issaugoti tranzakciju registro");
                        break;
                    }
                default:
                    dialog.show("Iveskite meniu punkta nuo 0 iki 4");
                    break;
            }

        }
    }
}
