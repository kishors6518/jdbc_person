package jdbc_person;

import java.sql.ResultSet;
import java.util.Scanner;

public class PersonMain {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome!!! \n1.Signup \n2.Login \n3.Exit");
		PersonCrud personCrud = new PersonCrud();

		while (true) {
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1: {
				System.out.println("Enter the id");
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter the name");
				String name = scanner.nextLine();
				System.out.println("Enter the phone number");
				long phno = scanner.nextLong();
				scanner.nextLine();
				System.out.println("Enter the mail");
				String email = scanner.nextLine();
				System.out.println("Enter the Password");
				String pass = scanner.nextLine();

				Person person = new Person();
				person.setId(id);
				person.setName(name);
				person.setEmail(email);
				person.setPhno(phno);
				person.setPass(pass);

				int count = personCrud.singUp(person);
				if (count != 0) {
					System.out.println("Sign up Succefully");
				} else {
					System.out.println("Please check the mistakes");
				}

				break;
			}
			case 2: {
				scanner.nextLine();
				System.out.println("Enter the Email");
				String email = scanner.next();
				System.out.println("Enter the Password");
				String pass = scanner.next();
				ResultSet set = personCrud.login();
				// int personId=-1;
				boolean flag = false;
				while (set.next()) {
					if ((set.getString("email").equals(email)) && (set.getString("password").equals(pass))) {
						flag = true;
						System.out.println("Login Successful");
						System.out.println("1.Display \n2.Update Info \n3.Delete Account \n4.Logout");
						System.out.println("Enter your choice");
						int op = scanner.nextInt();
						switch (op) {
						case 1: {
							System.out.println("ID:" + set.getInt("id") + ", Name:" + set.getString("name") + ", Phone:"
									+ set.getLong("phone") + ", Email:" + set.getString("email"));
							break;
						}
						case 2: {
							System.out.println("What data you update");
							System.out.println("1.Name \n2.Phone \n3.Email \n4.Password");
							System.out.println("Enter your choice");
							int op1 = scanner.nextInt();
							switch (op1) {
							case 1: {
								scanner.nextLine();
								System.out.println("Enter updated name");
								String name = scanner.nextLine();

								int result = personCrud.updateName(name, email);
								if (result != 0) {
									System.out.println("Data Updated");
								}
								break;
							}

							default:
								break;
							}

						}

							break;

						default:
							break;
						}

					}
				}
				if (!flag) {
					System.out.println("Please enter valid credentials");
				}

			}

			}
		}
	}

}
