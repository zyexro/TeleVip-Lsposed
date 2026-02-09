package com.my.televip.virtuals;

import java.lang.reflect.Field;
import java.util.ArrayList;

import de.robv.android.xposed.XposedHelpers;
import com.my.televip.ClientChecker;
import com.my.televip.Utils;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.FieldUtils;

public class TLRPC {
    public static class Peer {
        private final Object peer;

        public Peer(Object peer) {
            this.peer = peer;
        }
        public long getUser_id(){
            return XposedHelpers.getLongField(peer,"user_id");
        }
        public long getChat_id(){
            return XposedHelpers.getLongField(peer,"chat_id");
        }
        public long getChannel_id(){
            return XposedHelpers.getLongField(peer,"channel_id");
        }
    }
    public static class Message {
        public final Object message;
        private int id;
        public Message(Object message) {
            this.message = message;
        }
        public int getID(){
            if (id == 0){
                id = XposedHelpers.getIntField(message, "id");
            }
            return id;
        }
        public String getMessage(){
            return (String) XposedHelpers.getObjectField(message, "message");
        }
        public Object getMessages(){
            return XposedHelpers.getObjectField(message, "messages");
        }
        public Peer getFrom_id(){
            return new TLRPC.Peer(XposedHelpers.getObjectField(message, "from_id"));
        }
        public int getFlags(){
            return XposedHelpers.getIntField(message,"flags");
        }
        public void setFlags(int flags){
            XposedHelpers.setIntField(message, "flags", flags);
        }
    }

    public static class TL_updateDeleteChannelMessages {
        private final Object instance;

        public TL_updateDeleteChannelMessages(Object instance)
        {
            this.instance = instance;
        }

        public long getChannelID()
        {
            try
            {
                if (ClientChecker.check(ClientChecker.ClientType.Nekogram)) {
                    Field field = FieldUtils.getFieldFromMultiName(this.instance.getClass(), AutomationResolver.resolve("TLRPC$TL_updateDeleteChannelMessages", "channel_id", AutomationResolver.ResolverType.Field), long.class);
                    if (field != null)
                        return field.getLong(this.instance);
                }
                else
                    return FieldUtils.getFieldLongOfClass(this.instance, "channel_id");
            }
            catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
            return Long.MIN_VALUE;
        }

        public ArrayList<Integer> getMessages()
        {
            try
            {
                if (ClientChecker.check(ClientChecker.ClientType.Nekogram)) {
                    Field field = FieldUtils.getFieldFromMultiName(this.instance.getClass(), AutomationResolver.resolve("TLRPC$TL_updateDeleteChannelMessages", "messages", AutomationResolver.ResolverType.Field), ArrayList.class);
                    if (field != null) {
                        Object messages = field.get(this.instance);
                        if (messages != null)
                            return Utils.castList(messages, Integer.class);
                    }
                }
                else
                    return Utils.castList(FieldUtils.getFieldClassOfClass(this.instance, "messages"), Integer.class);
            }
            catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
            return null;
        }
    }

    public static class TL_updateDeleteMessages {
        private final Object instance;

        public TL_updateDeleteMessages(Object instance)
        {
            this.instance = instance;
        }

        public ArrayList<Integer> getMessages()
        {
            try
            {
                if (ClientChecker.check(ClientChecker.ClientType.Nekogram)) {
                    Field field = FieldUtils.getFieldFromMultiName(this.instance.getClass(), AutomationResolver.resolve("TLRPC$TL_updateDeleteMessages", "messages", AutomationResolver.ResolverType.Field), ArrayList.class);
                    if (field != null) {
                        Object messages = field.get(this.instance);
                        if (messages != null)
                            return Utils.castList(messages, Integer.class);
                    }
                }
                else
                    return Utils.castList(FieldUtils.getFieldClassOfClass(this.instance, "messages"), Integer.class);
            }
            catch (IllegalAccessException e)
            {
                Utils.log(e);
            }
            return null;
        }
    }
}
