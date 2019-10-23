package com.furious.golf.service.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GolfOpto {
    public static void main(String[] args) throws IOException {
        String file = "zozoproj.csv";
        String line = "";
        String cvsSplitBy = ",";
        List<GolfPlayerProjections> projections = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                try {
                    String[] golfer = line.split(cvsSplitBy);
                    GolfPlayerProjections playerProjections = new GolfPlayerProjections();
                    playerProjections.setPlayerName(golfer[3]);
                    playerProjections.setPlayerId(golfer[4]);
                    playerProjections.setSiteId(golfer[4]);
                    if (golfer.length > 18) {
                        playerProjections.setMax(Integer.valueOf(golfer[20]) + 5);

                        playerProjections.setProj(Double.valueOf(golfer[19]));
                    }
                    else {
                        playerProjections.setMax(0);

                        playerProjections.setProj(0);
                    }
                    playerProjections.setPos("G");

                    System.out.println("Golfer [name= " + golfer[0] + " , salary=" + golfer[20] + "]");
                    System.out.println(playerProjections.toString());
                    projections.add(playerProjections);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        GolfOptoSettings settings = new GolfOptoSettings();
        settings.setNumberLineups(20);
        settings.setUniquePlayers(3);

        GolfOpto.getBestLineups(settings, projections, "DraftKings");

    }

    public static List<GolfOptoLineup> getBestLineups(GolfOptoSettings settings,
        List<GolfPlayerProjections> projections, String site) {
        System.out.println("Opto Start " + new Date());
        Date start = new Date();

        int count = 1;
        Map<String, Integer> playerCountMap = new HashMap<>();
        List<GolfOptoLineup> bestLineups = new ArrayList<>();

        Map<String, GolfOptoPlayer> golfers = new HashMap<>();
        System.out.println("Unique players " + settings.getUniquePlayers());
        HashMap<String, GolfOptoPlayer> players = new HashMap<>();
        List<GolfOptoPlayer> mustPlayers = new ArrayList<>();
        for (GolfPlayerProjections projection : projections) {
            GolfOptoPlayer player = new GolfOptoPlayer();
            player.setPlayerId(projection.getPlayerId());
            player.setSiteId(String.valueOf(projection.getSiteId()));
            player.setPlayerName(projection.getPlayerName());
            player.setSalary(projection.getSalary());
            player.setActual(projection.getActual());

            double value = projection.getProj() * 1000;
            value = value / projection.getSalary();
            player.setValue(value);
            if (projection.isLocked()) {
                player.setProj(projection.getProj() + 50);
                if (projection.getMax() == 100) {
                    player.setProj(projection.getProj() + 100);
                    mustPlayers.add(player);
                }
            }
            else {
                player.setProj(projection.getProj());
            }

            player.setOwnership(projection.getOwnership());

            player.setMinExposure(0.0D);
            if (projection.getMax() == 0) {

                player.setMaxExposure(settings.getGlobalExposure());
            }
            else {
                System.out.println(player.getPlayerName() + " : " + projection.getMax());
                double exposure = settings.getNumberLineups() * ((double) projection.getMax() / 100);
                player.setMaxExposure(exposure);
            }
            golfers.put(player.getSiteId(), player);
            players.put(player.getSiteId(), player);
        }

        System.out.println("Opto cycle " + new Date());
        int optosize = 1;
        count = settings.getNumberLineups();

        for (int i = 0; i < count; i++) {

            System.out.println(
                "Best Lineups Size " + new Date() + " | " + bestLineups.size() + " For Lineup Count " + settings
                    .getNumberLineups() + " Player size " + players.size());
            List<GolfOptoLineup> optos = getOptos(players, settings, site, optosize);
            optos.sort(Comparator.comparingDouble(GolfOptoLineup::getProjPoints).reversed());
            System.out.println("Opto done " + new Date() + " size " + optos.size());

            if (optos.size() == 0) {
                ArrayList<GolfOptoPlayer> optoPlayers = new ArrayList<>();
                optoPlayers.addAll(new ArrayList<>(players.values()));
                optoPlayers.sort(Comparator.comparingDouble(GolfOptoPlayer::getValue).reversed());

                optosize = optosize + 1;
                System.out.println("PlayerSize " + players.size());
            }
            boolean maxPlayer = false;
            int counters = 0;
            for (int x = 0; x < optos.size(); x++) {
                boolean add = true;

                for (GolfOptoLineup optoLineup : bestLineups) {

                    if (optoLineup.getHash() == optos.get(x).getHash()) {

                        add = false;
                        counters = counters + 1;
                        if (counters >= optos.size()) {
                            count++;
                            optosize = optosize + 1;
                            System.out.println("Hash fail " + optosize);
                            break;
                        }
                    }
                    int uniqueCount = 0;
                    if (settings.getUniquePlayers() > 0) {
                        if (optos.get(x).getG1().getPlayerName().equals(optoLineup.getG1().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG1().getPlayerName().equals(optoLineup.getG2().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG1().getPlayerName().equals(optoLineup.getG3().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG1().getPlayerName().equals(optoLineup.getG4().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG1().getPlayerName().equals(optoLineup.getG5().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG1().getPlayerName().equals(optoLineup.getG6().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }

                        if (optos.get(x).getG2().getPlayerName().equals(optoLineup.getG1().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG2().getPlayerName().equals(optoLineup.getG2().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG2().getPlayerName().equals(optoLineup.getG3().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG2().getPlayerName().equals(optoLineup.getG4().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG2().getPlayerName().equals(optoLineup.getG5().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG2().getPlayerName().equals(optoLineup.getG6().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }

                        if (optos.get(x).getG3().getPlayerName().equals(optoLineup.getG1().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG3().getPlayerName().equals(optoLineup.getG2().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG3().getPlayerName().equals(optoLineup.getG3().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG3().getPlayerName().equals(optoLineup.getG4().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG3().getPlayerName().equals(optoLineup.getG5().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG3().getPlayerName().equals(optoLineup.getG6().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }

                        if (optos.get(x).getG4().getPlayerName().equals(optoLineup.getG1().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG4().getPlayerName().equals(optoLineup.getG2().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG4().getPlayerName().equals(optoLineup.getG3().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG4().getPlayerName().equals(optoLineup.getG4().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG4().getPlayerName().equals(optoLineup.getG5().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG4().getPlayerName().equals(optoLineup.getG6().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }

                        if (optos.get(x).getG5().getPlayerName().equals(optoLineup.getG1().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG5().getPlayerName().equals(optoLineup.getG2().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG5().getPlayerName().equals(optoLineup.getG3().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG5().getPlayerName().equals(optoLineup.getG4().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG5().getPlayerName().equals(optoLineup.getG5().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG5().getPlayerName().equals(optoLineup.getG6().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }

                        if (optos.get(x).getG6().getPlayerName().equals(optoLineup.getG1().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG6().getPlayerName().equals(optoLineup.getG2().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG6().getPlayerName().equals(optoLineup.getG3().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG6().getPlayerName().equals(optoLineup.getG4().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG6().getPlayerName().equals(optoLineup.getG5().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }
                        if (optos.get(x).getG6().getPlayerName().equals(optoLineup.getG6().getPlayerName())) {
                            uniqueCount = uniqueCount + 1;
                        }

                    }
                    if (uniqueCount <= (10 - settings.getUniquePlayers())) {
                        //System.out.println("Unique Good " +( 9 - settings.getUniquePlayers()) + " | " + uniqueCount);
                        //add = true;
                        //count++;
                        //break;

                    }
                    else {
                        //System.out.println("Unique Bad " + uniqueCount);

                        add = false;
                        counters = counters + 1;
                    }
                }
                if (counters >= optos.size()) {
                    count++;
                    optosize = optosize + 1;
                    System.out.println("Unique Bad " + optosize);
                    break;
                }
                if (add) {
                    maxPlayer = false;

                    GolfOptoLineup thisLineup = optos.get(x);
                    playerCountMap = setPlayerCount(playerCountMap, thisLineup.getG1().getSiteId());
                    Integer integer = playerCountMap.get(thisLineup.getG1().getSiteId());
                    if (integer >= thisLineup.getG1().getMaxExposure()) {
                        players.remove(thisLineup.getG1().getSiteId());
                        //break;
                        System.out.println("MAX " + thisLineup.getG1().getPlayerName() + " | " + thisLineup.getG1()
                            .getMaxExposure());
                        maxPlayer = true;
                    }

                    if (integer >= thisLineup.getG2().getMaxExposure()) {
                        players.remove(thisLineup.getG2().getSiteId());
                        //break;
                        System.out.println("MAX " + thisLineup.getG2().getPlayerName() + " | " + thisLineup.getG2()
                            .getMaxExposure());
                        maxPlayer = true;
                    }

                    if (integer >= thisLineup.getG3().getMaxExposure()) {
                        players.remove(thisLineup.getG3().getSiteId());
                        //break;
                        System.out.println("MAX " + thisLineup.getG3().getPlayerName() + " | " + thisLineup.getG3()
                            .getMaxExposure());
                        maxPlayer = true;
                    }

                    if (integer >= thisLineup.getG4().getMaxExposure()) {
                        players.remove(thisLineup.getG4().getSiteId());
                        //break;
                        System.out.println("MAX " + thisLineup.getG4().getPlayerName() + " | " + thisLineup.getG4()
                            .getMaxExposure());
                        maxPlayer = true;
                    }

                    if (integer >= thisLineup.getG5().getMaxExposure()) {
                        players.remove(thisLineup.getG5().getSiteId());
                        //break;
                        System.out.println("MAX " + thisLineup.getG5().getPlayerName() + " | " + thisLineup.getG5()
                            .getMaxExposure());
                        maxPlayer = true;
                    }

                    if (integer >= thisLineup.getG6().getMaxExposure()) {
                        players.remove(thisLineup.getG6().getSiteId());
                        //break;
                        System.out.println("MAX " + thisLineup.getG6().getPlayerName() + " | " + thisLineup.getG6()
                            .getMaxExposure());
                        maxPlayer = true;
                    }
                    bestLineups.add(optos.get(x));

                    if (bestLineups.size() >= settings.getNumberLineups()) {
                        break;
                    }
                    if (maxPlayer) {
                        break;
                    }
                    if (optosize > 5) {
                        break;
                    }
                }
            }
            if (optosize > 5) {
                System.out.println("optosize " + optosize);
                break;
            }
            System.out.println("counters " + counters);
            //System.out.println("Opto loop over " + new Date());
            if (bestLineups.size() >= settings.getNumberLineups()) {
                break;
            }
            else {
                count++;
                if (maxPlayer) {
                    System.out.println("max player");
                }
                else {
                    System.out.println("optosize " + optosize);
                    //optosize = optosize + 1;
                }
            }
        }

        int counter = 0;
        for (GolfOptoLineup lineup : bestLineups) {

            counter++;
            //System.out.println(lineup.toString() + " | " + lineup.getHash());
        }
        Iterator it = playerCountMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

        System.out.println(start + " | " + new Date() + " = " + counter);
        return bestLineups;
    }

    public static Map<String, Integer> setPlayerCount(Map<String, Integer> playerCountMap, String siteId) {

        Integer integer = playerCountMap.get(siteId);
        if (integer != null) {
            integer = integer + 1;
            playerCountMap.put(siteId, integer);
        }
        else {
            playerCountMap.put(siteId, 1);
        }

        return playerCountMap;
    }

    public static List<GolfOptoLineup> getOptos(HashMap<String, GolfOptoPlayer> players, GolfOptoSettings settings,
        String site, Integer optosize) {

        int innerOpto = optosize;
        int teamMax = 5;
        HashMap<String, Integer> teamCountMap = new HashMap<>();
        HashMap<Integer, GolfOptoLineup> lineupMlbHashMap = new HashMap<>();

        Collection<GolfOptoLineup> lineups = new ArrayList<>();

        List<GolfOptoPlayer> firstSlot = new ArrayList<>();
        ArrayList playerList = new ArrayList(players.values());
        firstSlot = getOptimal(playerList, 3);

        for (GolfOptoPlayer first : firstSlot) {
            GolfOptoLineup mainLineup = new GolfOptoLineup();
            HashMap<String, GolfOptoPlayer> playersFirst = new HashMap<>(players);
            mainLineup.setG1(first);
            playersFirst.remove(first.getSiteId());

            List<GolfOptoPlayer> secondPlayers = getOptimal(new ArrayList(playersFirst.values()), 3);
            for (GolfOptoPlayer second : secondPlayers) {
                Map<String, GolfOptoPlayer> playersSecond = new HashMap<>(playersFirst);
                GolfOptoLineup mainLineup2 = new GolfOptoLineup(mainLineup);
                mainLineup2.setG2(second);
                playersSecond.remove(second.getSiteId());

                List<GolfOptoPlayer> thirdPlayers = getOptimal(new ArrayList(playersSecond.values()), 3);
                for (GolfOptoPlayer third : thirdPlayers) {
                    Map<String, GolfOptoPlayer> playersThird = new HashMap<>(playersSecond);
                    GolfOptoLineup mainLineup3 = new GolfOptoLineup(mainLineup2);
                    mainLineup3.setG3(third);
                    playersThird.remove(third.getSiteId());

                    List<GolfOptoPlayer> fourthPlayers = getOptimal(new ArrayList(playersThird.values()), 3);
                    for (GolfOptoPlayer fourth : fourthPlayers) {
                        Map<String, GolfOptoPlayer> playersFourth = new HashMap<>(playersThird);
                        GolfOptoLineup mainLineup4 = new GolfOptoLineup(mainLineup3);
                        mainLineup4.setG4(fourth);
                        playersFourth.remove(fourth.getSiteId());

                        List<GolfOptoPlayer> fifthPlayers = getOptimal(new ArrayList(playersFourth.values()), 3);
                        for (GolfOptoPlayer fifth : fifthPlayers) {
                            Map<String, GolfOptoPlayer> playersFifth = new HashMap<>(playersFourth);
                            GolfOptoLineup mainLineup5 = new GolfOptoLineup(mainLineup4);
                            mainLineup5.setG5(fifth);
                            playersFifth.remove(fifth.getSiteId());

                            List<GolfOptoPlayer> sixthPlayers = getOptimal(new ArrayList(playersFifth.values()), 3);
                            for (GolfOptoPlayer sixth : sixthPlayers) {
                                GolfOptoLineup mainLineup6 = new GolfOptoLineup(mainLineup5);
                                mainLineup6.setG6(sixth);

                                if (site.equals("DraftKings")) {
                                    if (mainLineup6.getSalary() > 50000) {
                                        break;

                                    }
                                }

                                if (mainLineup6.getG1() != null && mainLineup6.getG2() != null
                                    && mainLineup6.getG3() != null
                                    && mainLineup6.getG4() != null
                                    && mainLineup6.getG5() != null
                                    && mainLineup6.getG6() != null) {
                                    lineupMlbHashMap.put(mainLineup6.getHash(), mainLineup6);
                                    lineups = lineupMlbHashMap.values();
                                }
                                else {
                                    System.out.println("Empty slot");
                                }

                            }
                        }
                    }

                }

            }

        }
        return new ArrayList(lineups);

    }

    public static List<GolfOptoPlayer> getOptimal(List<GolfOptoPlayer> players, int unique) {

        List<GolfOptoPlayer> returnList = new ArrayList<>();
        int secCount = unique;

        if (players != null) {
            if (players.size() == 1) {
                returnList.add(players.get(0));
                return returnList;
            }

            players.sort(Comparator.comparingDouble(GolfOptoPlayer::getValue).reversed());
            //get 2 low salary
            //System.out.println(players.size() + " " + unique);

            if (players.size() > 1 && players.size() >= secCount) {
                int count = secCount;
                for (int i = 0; i < count; i++) {
                    returnList.add(players.get(i));
                }

                players.sort(Comparator.comparingDouble(GolfOptoPlayer::getProj).reversed());
                if (players.size() < secCount) {
                    count = players.size();
                }
                else {
                    count = secCount;
                }
                for (int i = 0; i < count; i++) {
                    if (!returnList.get(0).equals(players.get(i))) {
                        returnList.add(players.get(i));
                    }
                }
            }
            else {
                int count = players.size();
                for (int i = 0; i < count; i++) {
                    returnList.add(players.get(i));
                }

                players.sort(Comparator.comparingDouble(GolfOptoPlayer::getProj).reversed());
                if (players.size() < players.size()) {
                    count = players.size();
                }
                else {
                    count = players.size();
                }
                for (int i = 0; i < count; i++) {
                    if (!returnList.get(0).equals(players.get(i))) {
                        returnList.add(players.get(i));
                    }
                }

            }
        }

        return returnList;
    }

    private static Map<String, GolfOptoPlayer> listToMap(List<GolfOptoPlayer> playerPool) {
        Map<String, GolfOptoPlayer> map = playerPool.stream()
            .collect(Collectors.toMap(GolfOptoPlayer::getSiteId, p -> p));

        return map;
    }
}
