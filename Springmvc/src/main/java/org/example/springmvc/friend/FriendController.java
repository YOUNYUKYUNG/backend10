package org.example.springmvc.friend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FriendController {

    private List<Friend> friendList = new ArrayList<>(Arrays.asList(
            new Friend("aa","케로로","keroro@naver.com"),
            new Friend("bb","타마마","tamama@naver.com")

    ));


    @GetMapping("/friend")
    public  String friend (Model model){
        model.addAttribute("friendList",friendList);
        return "friend/friend";
    }

    @GetMapping("/registerForm")
    public String registerForm() {
        return "friend/registerForm";
    }

    @PostMapping("/registerForm")
    public String save(@RequestParam("id") String id,
                       @RequestParam("name") String name,
                       @RequestParam("email") String email,
                       Model model) {
        Friend newFriend = new Friend(id, name, email);
        friendList.add(newFriend);
        model.addAttribute("friendList", friendList);
        return "redirect:/friend";
    }

    @GetMapping("/friendEdit")
    public String editForm(@RequestParam("id") String id, Model model) {
        Friend friend = friendList.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("friend", friend);
        return "friend/friendEdit";
    }

    @PostMapping("/friendEdit")
    public String update(@RequestParam("id") String id,
                         @RequestParam("name") String name,
                         @RequestParam("email") String email) {
        for (Friend friend : friendList) {
            if (friend.getId().equals(id)) {
                friend.setName(name);
                friend.setEmail(email);
                break;
            }
        }
        return "redirect:/friend/friendEdit";
    }


}
