package hashTable;

public class HashTableDemo {

    // 用hashTable实现雇员的管理
    public static void main(String[] args) {
        EmployeeHashTable employeeHashTable = new EmployeeHashTable(4);
        employeeHashTable.add(new Employee(1, "Angenela1"));
        employeeHashTable.add(new Employee(2, "Angenela2"));
        employeeHashTable.add(new Employee(3, "Angenela3"));
        employeeHashTable.add(new Employee(4, "Angenela4"));
        employeeHashTable.add(new Employee(5, "Angenela5"));

        System.out.println(employeeHashTable.getById(5));
    }
}

// 雇员
class Employee {
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

// hashTable
class EmployeeHashTable {
    private EmployeeLinkedList[] linkedLists;
    int size;

    public EmployeeHashTable(int size) {
        this.size = size;
        linkedLists = new EmployeeLinkedList[size];
        for (int i = 0; i < size; i++) {
            linkedLists[i] = new EmployeeLinkedList();
        }
    }

    public void add(Employee employee) {
        int hashCode = hashCode(employee.id);
        linkedLists[hashCode].add(employee);
    }

    public void showAll() {
        for (EmployeeLinkedList linkedList : linkedLists) {
            linkedList.showAll();
        }
    }

    public int hashCode(int id) {
        return id % size;
    }

    public Employee getById(int id) {
        int hashCode = hashCode(id);
        return linkedLists[hashCode].getById(id);
    }
}

// 雇员链表
class EmployeeLinkedList {
    public Employee head;

    public void add(Employee employee) {
        if (head == null) {
            head = employee;
        } else {
            Employee temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = employee;
        }
    }

    public void deleteById(int id) {
        Employee temp = head;
        if (temp.id == id && temp == head) {
            head = temp.next;
            return;
        }
        while (temp != null) {
            if (temp.next.id == id) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
        throw new RuntimeException("没有该id的雇员");
    }

    public Employee getById(int id) {
        Employee temp = head;
        while (temp != null) {
            if (temp.id == id) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void showAll() {
        Employee temp = head;
        System.out.println();
        while (temp != null) {
            System.out.print(temp + "   ");
            temp = temp.next;
        }
    }
}












