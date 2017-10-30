package lt.mesgalis.bullshit.controller;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/social")
public class SocialController {

	private Facebook apiFacebook;
	private ConnectionRepository connectionRepository;

	public SocialController(Facebook apiFacebook, ConnectionRepository connectionRepository) {
		this.apiFacebook = apiFacebook;
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping(path = "/info", method = RequestMethod.GET)
	public UserInfo getUserInfo() {
		if (apiFacebook.isAuthorized()) {
			return getUserInfo_facebook();
		} else {
			return null;
		}
	}

	@RequestMapping(path = "/profilePic", method = RequestMethod.GET)
	public byte[] getProfilePic() {
		if (apiFacebook.isAuthorized()) {
			return getProfilePic_facebook();
		} else {
			return null;
		}
	}

	private UserInfo getUserInfo_facebook() {
		UserInfo userInfo = new UserInfo();
		User userProfile = apiFacebook.userOperations().getUserProfile();
		userInfo.id = userProfile.getId();
		userInfo.name = userProfile.getName();
		return userInfo;
	}

	private byte[] getProfilePic_facebook() {
		return apiFacebook.userOperations().getUserProfileImage();
	}

	public static class UserInfo {

		public String id;
		public String name;
		public byte[] profilePic;
	}

}
