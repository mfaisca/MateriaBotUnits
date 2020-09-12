package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Kefka")
public class Kefka extends Unit{
	public Kefka() {
		super("Kefka");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8436, 1740, 8438};
				break;
			case HP:
				ids = new int[]{8437};
				break;
			case S1:
				ids = new int[]{8427, 8428, 1740};
				break;
			case S2:
				ids = new int[]{8431, 8432, 1740};
				break;
			case EX:
				ids = new int[]{8434};
				break;
			case AA:
				ids = new int[]{6665};
				break;
			case LD:
				ids = new int[]{9299};
				break;
			case BT:
				ids = new int[]{9300};
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
			case 8438: //Meteor+
				ret.fixMergeAbility(8461);
			case 1740: //Meteor
				ret.fixRemoveDispels();
				break;
			case 8428: //S1+
			case 8432: //S2+
				ret.addStaticHit("Enabled if target is debuffed", 0);
				break;
			case 8434: //EX
				ret.fixDelayHitData(8062);
				ret.getAilmentById(1571).removeEffect(114);
				ret.addStaticAilmentEffect(1571, "+10% BRV Damage per debuff on target");
				break;
			case 6665: //AA
				ret.getAilmentById(1287).getEffects().clear();
				ret.getAilmentById(1287).getEffects().add(new Ailment.EffectGrouping("+5% BRV Damage dealt per debuff on target"));
				ret.getAilmentById(1287).getEffects().add(new Ailment.EffectGrouping("+20% Max BRV"));
				break;
			case 9299: //LD
				ret.removeHitDataById(9771);
				ret.addStaticHit("Existing debuffs inflicted by self become framed");
				break;
			case 9300: //BT
				ret.getAilmentById(1735).getAuras().remove(744);
				ret.addStaticAilmentEffect(1735, "+5% self HP Damage dealt per debuff on target");
				break;
		}
		return ret;
	}
}