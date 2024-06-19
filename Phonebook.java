package phonebook;
import java.util.*;
import java.util.regex.Pattern;

public class Phonebook {
    //Хранит id записи и имя абонента
    protected HashMap<Integer, String> nameMap = new HashMap<>();
    //Хранит имя абонента и его телефон
    protected HashMap<Integer, String> phoneMap = new HashMap<>();
    protected Integer maxIndex = 0;
    private String phonePtrn = "\\+?[7-8]{1}\\d{3}-\\d{3}-\\d{2}-\\d{2}";
    
    //Извелкает номера телеофонов по имени абонента
    public List<String> getPhoneByName(final String str) {
        if (phoneMap.isEmpty() || nameMap.isEmpty() || !nameMap.containsValue(str)) {
            return null;
        }
        List<String> ret = new ArrayList<>();
        final Set<Map.Entry<Integer, String>> nameSet = nameMap.entrySet();
        for (Map.Entry<Integer, String> entry : nameSet) {
                if (entry.getValue() == str) {
                    ret.add(phoneMap.get(entry.getKey()));
            }
        }
        return ret;
    }
    
    //Извлекает имя абонента по телефону
    public String getNameByPhone(final String phone) {
        if (Pattern.matches(phonePtrn, phone) == false) {
            throw new IllegalArgumentException("Phone doesn\'t match format: +7(8)ddd-ddd-dd-dd");
        }
        if (phoneMap.isEmpty() || nameMap.isEmpty() || !phoneMap.containsValue(phone)) {
            return null;
        }
        final Set<Map.Entry<Integer, String>> phoneSet = phoneMap.entrySet();
        for (Map.Entry<Integer, String> entry : phoneSet) {
            if (entry.getValue() == phone) {
                Integer key = entry.getKey();
                final Set<Map.Entry<Integer, String>> nameSet = nameMap.entrySet();
                for (Map.Entry<Integer, String> entry2 : nameSet) {
                    if (entry2.getKey() == key) {
                        return entry2.getValue();
                    }
                }
            }
        }
        return null;
    }
    
    //Добавляет имя абонента и телефон
    public int addPhone(final String str, final String phone) {
        if (Pattern.matches(phonePtrn, phone) == false) {
            throw new IllegalArgumentException("Phone doesn\'t match format: +7(8)ddd-ddd-dd-dd");
        }
        if (str == "" || phone == "") {
            throw new IllegalArgumentException("Empty args are not allowed");
        }
        if (!nameMap.containsValue(str) && !phoneMap.containsValue(phone)) {
            nameMap.put(maxIndex, str);
            phoneMap.put(maxIndex, phone);
        }
        return maxIndex++;
    }
    
    //Удаляет имя абонента и телефон
    public void removePhone(final String str, final String phone) {
        if (Pattern.matches(phonePtrn, phone) == false) {
            throw new IllegalArgumentException("Phone doesn\'t match format: +7(8)ddd-ddd-dd-dd");
        }
        if (phoneMap.isEmpty() || nameMap.isEmpty()) {
            return;
        }
        if (str == "" || phone == "") {
            throw new IllegalArgumentException("Empty args are not allowed");
        }
        if (!nameMap.containsValue(str) || !phoneMap.containsValue(phone))
            return;
        final Set<Map.Entry<Integer, String>> nameSet = nameMap.entrySet();
        for (Map.Entry<Integer, String> entry : nameSet) {
            if (entry.getValue() == str) {
                final Set<Map.Entry<Integer, String>> phoneSet = phoneMap.entrySet();
                for (Map.Entry<Integer, String> entry2 : phoneSet) {
                    if (entry2.getValue() == phone) {
                        if (entry.getKey() == entry2.getKey()) {
                            nameMap.remove(entry.getKey());
                            phoneMap.remove(entry2.getKey());
                            return;
                        }
                    }
                }
            }
        }
    }
    
    //Извлекает id записи по имени
    public ArrayList<Integer> getIdsByName(final String str) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (nameMap.isEmpty()) {
            return null;
        }
        if (str != null) {
            final Set<Map.Entry<Integer, String>> set = nameMap.entrySet();
            for (Map.Entry<Integer, String> entry : set) {
                if (entry.getValue() == str) {
                    ret.add(entry.getKey());
                }
            }
            return ret;
        }
        return null;
    }

    //Извлекает id записи по телефону        
    public ArrayList<Integer> getIdsByPhone(final String phone) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (!Pattern.matches(phonePtrn, phone)) {
            throw new IllegalArgumentException("Phone doesn\'t match format: +7(8)ddd-ddd-dd-dd");
        }
        if (phoneMap.isEmpty()) {
            return null;
        }
        if (phone != null) {
            final Set<Map.Entry<Integer, String>> phoneSet = phoneMap.entrySet();
            final Set<Map.Entry<Integer, String>> nameSet = nameMap.entrySet();
            for (Map.Entry<Integer, String> entry1 : phoneSet) {
                if (entry1.getValue() == phone) {
                    for (Map.Entry<Integer, String> entry2 : nameSet) {
                        if (entry2.getKey() == entry1.getKey())
                            ret.add(entry2.getKey());
                    }
                }
            }
            return ret;
        }
        return null;
    }
}
