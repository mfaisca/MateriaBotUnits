package Unit7;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Reno")
public class Reno extends Unit{
	public Reno() {
		super("Reno");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{10274};
				break;
			case HP:
				ids = new int[]{10275};
				break;
			case S1:
				ids = new int[]{9934};
				break;
			case S2:
				ids = new int[]{9938};
				break;
			case EX:
				ids = new int[]{9942};
				break;
			case AA:
				ids = new int[]{9948};
				break;
			case LD:
				ids = new int[]{9944};
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
	boolean done = false;
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
//			case 9934: //S1
//				if(!done) {
//					done = true;
//					Ailment ail = ret.getAilmentById(1834);
//					ail.getEffects().get(2).rankData[0] = "-20-20";
//					ail.getEffects().get(2).rankData[1] = "-40-40";
//					ail.getEffects().get(3).rankData[0] = "-20-20";
//					ail.getEffects().get(3).rankData[1] = "-40-40";
//				}break;
			case 9944: //LD
				if(ret.getDetails().getAilments().get(0).getEffects().stream().anyMatch(a -> a.effectId == 58))
					ret.getDetails().getAilments().get(0).getEffects().remove(4);
				break;
		}
		return ret;
	}
}