package Assignments;
import java.util.*;

import javax.jws.soap.SOAPBinding.Use;

import java.io.*;
import java.nio.file.*;


public class song_list {

    static Scanner inputReader = new Scanner(System.in);
    
    // creating a class to produce song objects
    static class songs 
    {
        // my songs have these three attributes to them
        public String song_name;
        public String artist_name;
        public int play_count;

        // this is the constructor for the songs class
        songs(String song_name, String artist_name, int play_count)
        {
            this.song_name = song_name;
            this.artist_name = artist_name;
            this.play_count = play_count;            
        };

        // getter methods for attributes
        public String get_name(){
            return song_name;
        };

        public String get_artist(){
            return artist_name;
        };

        public int get_count(){
            return play_count;
        };
        
        //print method for songs
        public String print_value(){
            return("Song: "+ song_name +", Artist: "+ artist_name +", Play count: "+ play_count);
        };
    };

    static class list_songs {
        //declaring an array list of the songs object 
        public ArrayList <songs> songs_list = new ArrayList<songs>();

        // a method to add a song to the song list
        public void add_music(String i, String n, int q){
            songs_list.add(new songs(i, n, q));
        };

        // prints all products
        public void print_music(){
            for(songs song : songs_list){
                String temp = song.print_value();
                System.out.println(songs_list.indexOf(song) +". "+ temp);
            };
        };

        // method to removed an object fropm the array list by name of song
        public void remove_song_by_index(int song_index){
            for (int i = 0; i < songs_list.size(); i++){
                if (song_index == i){
                    songs_list.remove(i);
                    break;
                };
            };
        };

        // generate random set of 10 song names, with random artist names and random play count!
        Random r = new Random();

        public String random_words(){
            // finding the path to the current main directory holding song_list.java
            // to use, to create the absolute path to the word_list.txt file
            Path current_path = Paths.get("").toAbsolutePath();
            String my_file_path = current_path.toString()+ "\\word_list.txt";
            // creating an array list of strings to hold the words from word_list.txt
            ArrayList <String> words = new ArrayList<String>();
            // this chunk of code sees if the file exist and if so will scan/read it and add it to
            // the array list we created
            File file = new File(my_file_path);
            if (file.exists()){
                try{
                    // System.out.println("file exist!");
                    Scanner scan = new Scanner(file);
                    while(scan.hasNext()){
                        words.add(scan.nextLine().toString());
                    }
                    scan.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("This file does not exist");
            }
            // this coed should then select at random 2 words from the temp array list
            Random rand = new Random();
            int first = rand.nextInt(10000);
            int second = rand.nextInt(10000);
            try{
                String first_word = words.get(first);
                String second_word = words.get(second);
                return(first_word +" "+ second_word);
            } catch(Exception e){
                e.printStackTrace();
                return(null);
            }
        }

        public int random_playcount(){
            // constants needed in the code
            int max = 9;
            int min = 6;
            String play_count = "";
            // code that will generate a random 7 or up to 10 digit number
            int first_rand = (int)Math.floor(Math.random() *(max - min +1) +min);
            for(int i=0; i < first_rand; i++){
                Random rand = new Random();
                int digit = rand.nextInt(10);
                play_count = play_count + String.valueOf(digit);
            }
            // System.out.println(play_count);
            return (Integer.valueOf(play_count));
        }

        public void above_the_playcount(int play_count){
            for (int i = 0; i < songs_list.size(); i++){
                if (songs_list.get(i).get_count() > play_count){
                    songs temp = songs_list.get(i);
                    System.out.println(temp.print_value());
                };
            };
        };
    };
    public static void main(String []args){
        list_songs list_of_songs = new list_songs();

        for(int i = 0; i < 10; i++){
            // System.out.println();
            list_of_songs.add_music(list_of_songs.random_words(), list_of_songs.random_words(), list_of_songs.random_playcount());
        };

        boolean USER_INTEREST = false;
        System.out.println(" \n Hello user \n What would you like to do today?");
        while (!USER_INTEREST){
            System.out.println(" 1. Add a song");
            System.out.println(" 2. Remove a song \n 3. See a list of songs \n 4. See songs above a given playcount");
            System.out.println(" 5. Exit \n Please select an option by its numbers ;)");
            
            boolean VALID_CHOICE = false;
            int user_choice = 0;
            while (!VALID_CHOICE){
                try{
                    user_choice = inputReader.nextInt();
                    inputReader.nextLine();
                    if (0 < user_choice && user_choice< 6){
                        VALID_CHOICE = true;
                    }
                    else {
                        System.out.println("I dont know that task, please give me another");
                        System.out.println("after inputreader.next()");
                    }
                } catch (Exception e) {
                    System.out.println("I dont know that task, please give me another");
                    inputReader.next();
                }
            }

            if (user_choice == 1){
                System.out.println("To add song please enter");
                System.out.println("Song name:");
                String name = inputReader.nextLine();
                System.out.println("Artist name:");
                String artist = inputReader.nextLine();
                System.out.println("Play count:");
                int count = inputReader.nextInt();
                inputReader.nextLine();
                list_of_songs.add_music(artist, name, count);
            };

            if (user_choice == 2){
                System.out.println("give me the index number (the number onm the left of each song) of the song you wish to remove from the list");
                int remove_index = inputReader.nextInt();
                inputReader.nextLine();
                list_of_songs.remove_song_by_index(remove_index);
            };

            if (user_choice == 3){
                list_of_songs.print_music();
            };

            if (user_choice == 4){
                System.out.println("Enter a playcount, and we will show you all songsin the list that have higher playcounts");
                int playcount_input = inputReader.nextInt();
                inputReader.nextLine();
                list_of_songs.above_the_playcount(playcount_input);
            };

            if (user_choice == 5){
                USER_INTEREST = true;
            };
        };
    }
}