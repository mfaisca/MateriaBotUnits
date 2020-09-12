package Unit7;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ailment.EffectGrouping;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Barret")
public class Barret extends Unit{
	public Barret() {
		super("Barret");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{3350};
				break;
			case HP:
				ids = new int[]{2870};
				break;
			case S1:
				ids = new int[]{5799};
				break;
			case S2:
				ids = new int[]{5801};
				break;
			case EX:
				ids = new int[]{5803, 5457};
				break;
			case AA:
				ids = new int[]{5500};
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
			case 5801: //S2
				if(ret.getAilmentById(814).getEffects().stream().noneMatch(e -> e.effectId == Ailment.EffectType.E60.getId())) {
					Ailment ail = ret.getAilmentById(814);
					ail.getEffects().add(new EffectGrouping(Ailment.EffectType.E60.getId()));
					ail.getAuras().get(29).ailmentEffect = Ailment.EffectType.E1.getId();
					ail.getAuras().get(29).target = Ailment.Target.Party.getId();
					ail.getAuras().get(32).ailmentEffect = Ailment.EffectType.E5.getId();
					ail.getAuras().get(32).target = Ailment.Target.Party.getId();
				}
				break;
			case 5803:
				ret.addStaticAilmentEffect(1126, "Triggers 「**" + this.getSpecificAbility(5457).getName() + "**」 after an HP hit");
				break;
			case 5457:
				ret.setName("Satellite");
				break;
			case 5500: //AA
				Ailment ail = ret.getAilmentById(1167);
				String[] rankData = ail.getAuras().get(328).rankData;
				rankData[1] = rankData[0];
				rankData = ail.getAuras().get(329).rankData;
				rankData[1] = rankData[0];
				ail.getAuras().get(330).ailmentEffect = Ailment.EffectType.E67.getId();
				break;
		}
		return ret;
	}
}