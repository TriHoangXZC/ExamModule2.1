package manage;

import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactManage {
    public final static String PATH_CONTACT = "src/data/contact.csv";
    ArrayList<Contact> contacts = readCSV();
    Scanner sc = new Scanner(System.in);

    public ContactManage() {
    }

    public ArrayList<Contact> displayContactList() {
        return contacts;
    }

    public Contact creatContact() {
        System.out.println("Nhập số điện thoại (9 hoặc 10 số) : ");
        String phoneNumber = sc.nextLine();
        if (checkPhoneNumber(phoneNumber)) {
            System.out.println("Số điện thoại đã được lưu trong danh bạ");
            return null;
        }
        System.out.println("Họ và tên : ");
        String name = sc.nextLine();
        System.out.println("Giới tính : ");
        String gender = sc.nextLine();
        System.out.println("Địa chỉ : ");
        String address = sc.nextLine();
        System.out.println("Facebook : ");
        String facebook = sc.nextLine();
        System.out.println("Email : ");
        String email = sc.nextLine();
        if (checkRegexPhoneNumber(phoneNumber) && checkRegexEmail(email)) {
            return new Contact(phoneNumber, name, gender, address, facebook, email);
        } else {
            System.out.println("Lỗi số điện thoại hoặc email");
            return null;
        }
    }

    public void addContact() {
        Contact contact = creatContact();
        if (contact != null) {
            contacts.add(contact);
            writeCSV(contacts);
            System.out.println("Thêm vào danh bạ thành công!");

        } else {
            System.out.println("Thêm vào danh bạ không thành công!");
        }
    }

    public void updateContact(String phoneNumber) {
        if (checkPhoneNumber(phoneNumber)) {
            System.out.println("Họ và tên mới: ");
            String name = sc.nextLine();
            System.out.println("Giới tính mới: ");
            String gender = sc.nextLine();
            System.out.println("Địa chỉ mới: ");
            String address = sc.nextLine();
            System.out.println("Facebook mới: ");
            String facebook = sc.nextLine();
            System.out.println("Email mới: ");
            String email = sc.nextLine();
            for (Contact contact : contacts) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    if (checkRegexPhoneNumber(phoneNumber) && checkRegexEmail(email)){
                        contact.setName(name);
                        contact.setGender(gender);
                        contact.setAddress(address);
                        contact.setFacebook(facebook);
                        contact.setEmail(email);
                    } else {
                        System.out.println("Lại sai số hoặc email nữa rồi");
                    }
                }
            }
            writeCSV(contacts);
            System.out.println("Cập nhật thành công!");
        } else {
            String phoneNumber2;
            do {
                phoneNumber2 = sc.nextLine();
                System.out.println("Không tìm được danh bạ với số điện thoại trên.");
                System.out.println("Nhập lại");
                updateContact(phoneNumber2);
            } while (!phoneNumber2.equals(""));
        }
    }

    public void deleteContact(String phoneNumber) {
        if (checkPhoneNumber(phoneNumber)) {
            for (Contact contact : contacts) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    System.out.println("Bạn có muốn xóa (Y)");
                    String select = sc.nextLine();
                    if (select.equalsIgnoreCase("Y")) {
                        contacts.remove(contact);
                        writeCSV(contacts);
                        System.out.println("Xóa thành công!");
                        return;
                    } else {
                        System.out.println("Không xóa số này!");
                    }
                }
            }
        } else {
            System.out.println("Không tìm được danh bạ với số điện thoại trên.");
        }
    }

    public ArrayList<Contact> findContact(String findInput) {
        ArrayList<Contact> contactsList = new ArrayList<>();
        System.out.println("Bạn muốn tìm theo số hay theo tên? (1 để tìm theo số, 2 để tìm theo tên)");
        int choice = sc.nextInt();
        sc.nextLine();
        Pattern pattern = Pattern.compile(findInput);
        Matcher matcher;
        if (choice == 1) {
            for (Contact contact : contacts) {
                matcher = pattern.matcher(contact.getPhoneNumber());
                if (matcher.find()) {
                    contactsList.add(contact);
                }
            }
        } else if (choice == 2) {
            for (Contact contact : contacts) {
                matcher = pattern.matcher(contact.getName());
                if (matcher.find()) {
                    contactsList.add(contact);
                }
            }
        }
        return contactsList;
    }

    public void writeCSV(ArrayList<Contact> contactsList) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_CONTACT));
            for (Contact contact : contactsList) {
                bufferedWriter.write(contact.getPhoneNumber() + "," + contact.getName() + "," +
                        contact.getGender() + "," + contact.getAddress() + "," + contact.getFacebook() + "," + contact.getEmail());
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
            System.out.println("Ghi thành công!");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Contact> readCSV() {
        ArrayList<Contact> contactsList = new ArrayList<>();
        File file = new File(PATH_CONTACT);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] output = line.split(",");
                    contactsList.add(new Contact(output[0], output[1], output[2], output[3], output[4], output[5]));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return contactsList;
    }


    public boolean checkPhoneNumber(String phoneNumber) {
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRegexPhoneNumber(String phoneNumber) {
        String regex = "(([^0]\\d{8})$)|((0\\d{9})$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.find();
    }


    public boolean checkRegexEmail(String email) {
        String regex = "^[a-zA-Z0-9]+@[a-z]+\\.(com|vn)+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}

