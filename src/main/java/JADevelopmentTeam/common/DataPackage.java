package JADevelopmentTeam.common;

public class DataPackage {
    private Object data;
    private Info info;
    public enum Info {Stone,StoneTable,PlayerColor
    }

    public DataPackage(Object data, Info info) {
        this.data = data;
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public Info getInfo() {
        return info;
    }
}
