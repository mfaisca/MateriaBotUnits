package Unit9;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Zidane")
public class Zidane extends Unit{
	public Zidane() {
		super("Zidane", "yitan", "monkey");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8477};
				break;
			case HP:
				ids = new int[]{8341};
				break;
			case S1:
				ids = new int[]{8330};
				break;
			case S2:
				ids = new int[]{8334};
				break;
			case EX:
				ids = new int[]{8339, 8340};
				break;
			case AA:
				ids = new int[]{5358};
				break;
			case LD:
				ids = new int[]{};
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
			case 8339:{ //EX+
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enables 「**Mug**」 for 1 use");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(adh);
				}break;
			case 8340:{ //EX+ (HP+)
				ret.removeHitDataById(5333);
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enabled after using 「**Booster 8**」 for 1 use");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
				}break;
		}
		return ret;
	}
}