package fi.metropolia.martikas.commuterreminder.model;

public class Query {
    private String text;
    private String parser;
    private String[] tokens;
    private long size;
    private Source[] sources;
    private boolean queryPrivate;
    private String lang;

    public String getText() { return text; }
    public void setText(String value) { this.text = value; }

    public String getParser() { return parser; }
    public void setParser(String value) { this.parser = value; }

    public String[] getTokens() { return tokens; }
    public void setTokens(String[] value) { this.tokens = value; }

    public long getSize() { return size; }
    public void setSize(long value) { this.size = value; }

    public Source[] getSources() { return sources; }
    public void setSources(Source[] value) { this.sources = value; }

    public boolean getQueryPrivate() { return queryPrivate; }
    public void setQueryPrivate(boolean value) { this.queryPrivate = value; }

    public String getLang() { return lang; }
    public void setLang(String value) { this.lang = value; }
}
