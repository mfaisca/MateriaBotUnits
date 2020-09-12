package Unit7;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Zack")
public class Zack extends Unit{
	public Zack() {
		super("Zack", "soldier 1st class", "soldier 1st class");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6481};
				break;
			case HP:
				ids = new int[]{6482};
				break;
			case S1:
				ids = new int[]{3935};
				break;
			case S2:
				ids = new int[]{3937};
				break;
			case EX:
				ids = new int[]{6480};
				break;
			case AA:
				ids = new int[]{4371};
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
			case 6481: //BRV+
				ret.removeAilmentById(47);
				break;
			case 3935: //S1   //Removed second invisible buff and merged
				if(ret.getDetails().getAilments().stream().anyMatch(a -> a.getId() == 239)) {
					ret.removeAilmentById(239);
					Ailment a = ret.getAilmentById(236);
					a.setFake("Physical Resist", null, "buffInvisible");
					a.getEffects().add(new Ailment.EffectGrouping(21));
				}
				break;
		}
		return ret;
	}
}