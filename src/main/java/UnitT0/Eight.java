package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Eight")
public class Eight extends Unit{
	public Eight() {
		super("Eight", "eighty");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "JP":
						ids = new int[]{10051}; break;
					case "GL":
						ids = new int[]{6289}; break;
				}
				break;
			case HP:
				switch(region) {
					case "JP":
						ids = new int[]{10080, 10110}; break;
					case "GL":
						ids = new int[]{6598, 6292}; break;
				}
				break;
			case S1:
				switch(region) {
					case "JP":
						ids = new int[]{10062}; break;
					case "GL":
						ids = new int[]{5939}; break;
				}
				break;
			case S2:
				switch(region) {
					case "JP":
						ids = new int[]{10064}; break;
					case "GL":
						ids = new int[]{5945}; break;
				}
				break;
			case EX:
				ids = new int[]{6300, 6302};
				break;
			case AA:
				ids = new int[]{6306};
				break;
			case LD:
				ids = new int[]{10074, 10077};
				break;
			case BT:
				ids = new int[]{};
				break;
			case CA:
				ids = new int[]{};
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i)).collect(Collectors.toList());
	}
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 6292:
			case 10110:
				ret.fixRemoveDispels();
				ret.addStaticHit("Enabled after using 「**" + this.getSpecificAbility(10051).getName() + "**」", 0);
				break;
			case 6302:
				ret.setName("Breaksight Blow");
				break;
		}
		return ret;
	}
}