
package io.pick5.domain.user;

import lombok.Data;

@Data
public class FormattedMessage {

    private String name;
    private String message;

    public FormattedMessage(){
        this.name = "Default";
        this.message = "Hello World!";
    }

    public FormattedMessage(String name){
        this.name = name;
        this.message = "Hello "+this.name+"!";
    }

}
