package com.example.psyhologyrf;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;

import com.example.psyhologyrf.ui.gallery.GalleryFragment;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.regex.Pattern;


public class SimpleBot {
    final String[] COMMON_PHRASES = new String[]{"Нет ничего ценнее слов, сказанных к месту и ко времени.", "Порой молчание может сказать больше, нежели уйма слов.", "Перед тем как писать/говорить всегда лучше подумать.", "Вежливая и грамотная речь говорит о величии души.", "Приятно когда текст без орфографических ошибок.", "Многословие есть признак неупорядоченного ума.", "Слова могут ранить, но могут и исцелять.", "Записывая слова, мы удваиваем их силу.", "Кто ясно мыслит, тот ясно излагает.", "Боюсь Вы что-то не договариваете.", "Я не уловил суть давайте сначало."};
    final String[] ELUSIVE_ANSWERS = new String[]{"Вопрос непростой, прошу тайм-аут на раздумья.", "Не уверен, что располагаю такой информацией.", "Может лучше поговорим о чём-то другом?", "Простите, но это очень личный вопрос.", "Не уверен, что Вам понравится ответ.", "Поверьте, я сам хотел бы это знать.", "Вы действительно хотите это знать?", "Уверен, Вы уже догадались сами.", "Зачем Вам такая информация?", "Давайте сохраним интригу?"};
    final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {
        {
            // put hello
            this.put("хай", "hello");
            this.put("привет", "hello");
            this.put("здорово", "hello");
            this.put("здасьте", "hello");
            this.put("здравствуй", "hello");
            this.put("здравствуйте", "hello");
            // put hello
            this.put("кто\\s.*ты", "who");
            this.put("ты\\s.*кто", "who");
            // put who
            this.put("как\\s.*зовут", "name");
            this.put("как\\s.*имя", "name");
            this.put("есть\\s.*имя", "name");
            this.put("какое\\s.*имя", "name");
            // put name
            this.put("как\\s.*дела", "howareyou");
            this.put("как\\s.*жизнь", "howareyou");
            // put howareyou
            this.put("зачем\\s.*тут", "whatdoyoudoing");
            this.put("зачем\\s.*здесь", "whatdoyoudoing");
            this.put("что\\s.*делаешь", "whatdoyoudoing");
            this.put("чем\\s.*занимаешься", "whatdoyoudoing");
            // put whatdoyoudoing
            this.put("что\\s.*нравится", "whatdoyoulike");
            this.put("что\\s.*любишь", "whatdoyoulike");
            // put whatdoyoulike
            this.put("кажется", "iamfeelling");
            this.put("чувствую", "iamfeelling");
            this.put("испытываю", "iamfeelling");
            // put iamfeelling
            this.put("^да", "yes");
            this.put("согласен", "yes");
            // put yes
            this.put("который\\s.*час", "whattime");
            this.put("сколько\\s.*время", "whattime");
            // put whattime
            this.put("что ты умеешь?", "scill");
            this.put("что умеешь?", "scill");
            this.put("что ты умеешь", "scill");
            // put scill
            this.put("расскажи анекдот", "anecdot");
            this.put("анекдот", "anecdot");
            // put anecdot
            this.put("прощай", "bye");
            this.put("увидимся", "bye");
            this.put("до\\s.*свидания", "bye");
            // put anecdot
            this.put("\\s.*депрессия", "depression");
            this.put("\\s.*дипрессия", "depression");
            this.put("я\\s.*не хочу", "depression");
            // put depression
        }
    };
    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {
        {
            this.put("hello", "Здравствуйте, рад Вас видеть!.");
            this.put("who", "Я обычный бот!.");
            this.put("name", "Зовите меня Фрейди! :)");
            this.put("howareyou", "Спасибо, что интересуетесь. У меня всё хорошо!.");
            this.put("whatdoyoudoing", "Я пробую общаться с людьми!.");
            this.put("whatdoyoulike", "Мне нравиться думать что я не просто программа.");
            this.put("iamfeelling", "Как давно это началось? Расскажите чуть подробнее.");
            this.put("yes", "Согласие есть продукт при полном непротивлении сторонс эр!.");
            this.put("bye", "До свидания. Надеюсь, ещё увидимся сэр!. ☻");
            this.put("scill", "сэр,я могу рассказать анекдот, подсказать время или просто поговорить ☆.");
            this.put("anecdot", "я пошутил, я не знаю анекдотов ☺.");
            this.put("depression", "я могу вам с этим помочь, давайте начнём общение.");
        }
    };
    Pattern pattern;
    Random random = new Random();
    Date date = new Date();


    public String sayInReturn(String msg, boolean ai) {
        String say = msg.trim().endsWith("?") ? this.ELUSIVE_ANSWERS[this.random.nextInt(this.ELUSIVE_ANSWERS.length)] : this.COMMON_PHRASES[this.random.nextInt(this.COMMON_PHRASES.length)];
        if (ai) {
            String message = String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
            Iterator var5 = this.PATTERNS_FOR_ANALYSIS.entrySet().iterator();

            while(var5.hasNext()) {
                Entry<String, String> o = (Entry)var5.next();
                this.pattern = Pattern.compile((String)o.getKey());
                if (this.pattern.matcher(message).find()) {
                    if (((String)o.getValue()).equals("whattime")) {
                        return this.date.toString();
                    }

                    return (String)this.ANSWERS_BY_PATTERNS.get(o.getValue());
                }
            }
        }

        return say;
    }

}
