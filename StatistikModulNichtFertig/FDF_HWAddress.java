import java.net.NetworkInterface;
import java.util.Collections;

public class FDF_HWAddress {
	public static String getMacAddress() throws Exception {
		String result = "";
		try {
			for (NetworkInterface ni : Collections.list(NetworkInterface
					.getNetworkInterfaces())) {
				byte[] hardwareAddress = ni.getHardwareAddress();
				if (hardwareAddress != null) {
					for (int i = 0; i < hardwareAddress.length; i++) {
						result += String.format((i == 0 ? "" : "") + "%02X",
								hardwareAddress[i]);
					}
					if (result.length() > 0 && !ni.isLoopback()) {
						return result;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		try {
			System.out.println(getMacAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}