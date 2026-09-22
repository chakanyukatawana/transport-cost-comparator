# Fix Navigation Bar Overlap and Click Issue

The top navigation bar is overlapping with the system status bar (clock, battery, etc.) because the app is running in **Edge-to-Edge** mode. This is the default behavior in Android 15 (API 35), where the app content is drawn behind the system bars. Because the layout doesn't account for these "insets," the text overlaps and the "?" button becomes hard or impossible to click as the system icons capture the touch events.

## Proposed Changes

### [Component Name] UI Fixes

#### [MODIFY] [MainActivity.java](file:///C:/Users/chaka/AndroidStudioProjects/TransportCostComparator/app/src/main/java/com/example/transportcostcomparator/MainActivity.java)
- Add window insets handling to the top navigation bar to push it down below the status bar.
- Enable Edge-to-Edge explicitly for consistent behavior.

#### [MODIFY] [HelpActivity.java](file:///C:/Users/chaka/AndroidStudioProjects/TransportCostComparator/app/src/main/java/com/example/transportcostcomparator/HelpActivity.java)
- Add window insets handling to the top navigation bar.
- Enable Edge-to-Edge explicitly.

#### [MODIFY] [activity_main.xml](file:///C:/Users/chaka/AndroidStudioProjects/TransportCostComparator/app/src/main/res/layout/activity_main.xml)
- Add an ID to the top `LinearLayout` (navigation bar) to reference it in code.
- Add `android:background="?attr/selectableItemBackgroundBorderless"` to navigation items for visual feedback.
- Increase padding for better touch targets.

#### [MODIFY] [activity_help.xml](file:///C:/Users/chaka/AndroidStudioProjects/TransportCostComparator/app/src/main/res/layout/activity_help.xml)
- Add an ID to the top `LinearLayout` (navigation bar).
- Add visual feedback and padding to navigation items.

## Verification Plan

### Manual Verification
1. Run the app on the emulator.
2. Verify that the blue navigation bar is now below the status bar icons (clock, battery).
3. Verify that clicking the "?" button successfully opens the Help screen.
4. Verify that clicking navigation items provides a ripple effect (visual feedback).
