package Unit9;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.Details.Hit_Data;
import com.materiabot.GameElements.Ability.Details.Hit_Data.EffectType;
import com.materiabot.GameElements.Ability.Details.Hit_Data.Target;
import com.materiabot.GameElements.Ability.Details.Hit_Data.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Vivi")
public class Vivi extends Unit{
	public Vivi() {
		super("Vivi", "bibi");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9277};
				break;
			case HP:
				ids = new int[]{9278};
				break;
			case S1:
				ids = new int[]{9293}; 
				break;
			case S2:
				ids = new int[]{9295}; 
				break;
			case EX:
				ids = new int[]{9276, 9287}; 
				break;
			case AA:
				ids = new int[]{5755};
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
			case 9295: //S2 JP
				if(ret.getDetails().getHits().stream().noneMatch(h -> h.getType().equals(Type.BRV))) {
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Magic, Type.BRV, Target.ST, 90, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Magic, Type.BRV, Target.ST, 90, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Magic, Type.BRV, Target.ST, 90, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Magic, Type.BRV, Target.ST, 90, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Magic, Type.HP, Target.ST);
				}
				break;
			case 9276: //EX
				if(ret.getAilmentById(832).getEffects().stream().noneMatch(eg -> eg.fakeDesc != null))
					ret.getAilmentById(832).getEffects().add(new Ailment.EffectGrouping("Enables 「**Double Fire**」"));
				break;
			case 9287: //EX HP+ JP
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Dispels 「**Double Black Magic**」");
				if(!ret.getDetails().getHits().contains(adh)) {
					ret.getDetails().getHits().addAll(ret.getDetails().getHits());
					ret.getDetails().getHits().add(0, adh);
					adh = new Ability.Details.Hit_Data();
					adh.setEffect(new Ability.Details.Hit_Data.Effect(EffectType.E41, Hit_Data.BasedOnStat.Stat2.getId()));
					adh.setArguments(new Integer[] {100});
					adh.setTarget(Target.Self);
					ret.getDetails().getHits().add(7, adh);
				}
				break;
		}
		return ret;
	}
}