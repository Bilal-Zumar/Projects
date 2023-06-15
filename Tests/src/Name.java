public class Name {


    // data fields to represent first, middle, and last name. (THESE ARE THE ONLY ALLOWED DATA FIELDS)/

    private String f_name;
    private String m_name;
    private String l_name;

	/*public Name(String f, String m, String l) -- constructor.
	The name should be taken as parameters and then stored in the instance data in the case given; don't convert to all upper or lower case. */

    public Name(String f, String m, String l) {
        f_name = f;
        m_name = m;
        l_name = l;
    }

    /*public String getFirst() -- returns the first name  (accessor method) */

    public String getFirst() {
        return f_name;
    }

    /* public String getMiddle() -- returns the middle name (accessor method)*/

    public String getMiddle() {
        return m_name;
    }


    public String getLast() {
        return l_name;
    }


    public String firstMiddleLast() {
        return f_name + ", " + m_name + " " + l_name;
    }


    public boolean equals(Name otherName) {
        return f_name.equals(otherName.f_name) && m_name.equals(otherName.m_name) && l_name.equals(otherName.l_name);
    }

    public String initials() {
        return f_name.substring(0, 1).toUpperCase() + m_name.substring(0, 1).toUpperCase() + l_name.substring(0, 1).toUpperCase();

    }

    public int length() {
        return f_name.length() + m_name.length() + l_name.length();
    }


}