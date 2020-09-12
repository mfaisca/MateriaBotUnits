package Unit7;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Vincent")
public class Vincent extends Unit{
	public Vincent() {
		super("Vincent");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5070};
				break;
			case HP:
				ids = new int[]{5126};
				break;
			case S1:
				ids = new int[]{5063, 5064};
				break;
			case S2:
				ids = new int[]{5067, 5068};
				break;
			case EX:
				ids = new int[]{5146};
				break;
			case AA:
				ids = new int[]{5033};
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
			case 5064:
			case 5068:{
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enabled when target has 3+ debuffs");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
				break;}
			case 5146:{
				Ailment ail = ret.getAilmentById(1055);
				ail.getEffects().get(1).effectId = Ailment.EffectType.E61.getId();
				ail.getEffects().get(1).rankData[0] = ail.getEffects().get(1).rankData[1];
				}break;
			case 5033:{
				Ailment ail = ret.getAilmentById(1059);
				ail.getAuras().get(261).ailmentEffect = Ailment.EffectType.E67.getId();
				ail.getAuras().get(261).target = Ailment.Target.Party.getId();
				}break;
		}
		return ret;
	}
}